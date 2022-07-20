/*
 * Copyright 2020 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.tree.expression.function.number;

import walkingkooka.Cast;
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionNumberSign;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

// https://exceljet.net/excel-functions/excel-roman-function
// https://support.google.com/docs/answer/3094153?hl=en
final class StringExpressionFunctionRoman<C extends ExpressionEvaluationContext> extends StringExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionRoman<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final StringExpressionFunctionRoman<?> INSTANCE = new StringExpressionFunctionRoman<>();

    /**
     * Private ctor
     */
    private StringExpressionFunctionRoman() {
        super("roman");
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<ExpressionNumber> FORM = ExpressionFunctionParameterName.with("form")
            .optional(ExpressionNumber.class)
            .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = Lists.of(
            NUMBER,
            FORM
    );

    // https://github.com/apache/poi/blob/7eaca60a1a364ce6e232363d27823e971a992705/poi/src/main/java/org/apache/poi/ss/formula/functions/Roman.java

    @Override
    public String apply(final List<Object> parameters,
                        final C context) {
        this.checkParameterCount(parameters);

        final ExpressionNumber number = NUMBER.getOrFail(parameters, 0);
        if (number.sign() == ExpressionNumberSign.NEGATIVE) {
            throw new IllegalArgumentException("Invalid number " + number + " < 0");
        }

        final ExpressionNumber form = FORM.get(parameters, 1)
                .orElse(context.expressionNumberKind().zero());
        final int formInteger = form.intValueExact();
        switch (formInteger) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                break;
            default:
                throw new IllegalArgumentException("Invalid form " + form + " must be between 0 and 4");
        }

        return format(
                number.intValue(),
                formInteger
        );
    }

    private String format(final int value,
                          final int form) {
        String result = integerToRoman(value);

        return 0 == form ? result : makeConcise(result, form);
    }

    // direct copy with formatting
    // of https://github.com/apache/poi/blob/7eaca60a1a364ce6e232363d27823e971a992705/poi/src/main/java/org/apache/poi/ss/formula/functions/Roman.java

    /**
     * Classic conversion.
     *
     * @param number the number
     */
    private String integerToRoman(int number) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            while (number >= VALUES[i]) {
                number -= VALUES[i];
                result.append(ROMAN[i]);
            }
        }
        return result.toString();
    }

    /**
     * Use conversion rule to factor some parts and make them more concise
     *
     * @param input the input string
     * @param form  the level of conciseness [0..4] with 4 being most concise and simplified
     */
    @SuppressWarnings("lgtm[java/index-out-of-bounds]")
    private String makeConcise(final String input, final int form) {
        String result = input;
        for (int i = 0; i <= form && i <= 4 && form > 0; i++) {
            if (i == 1 && form > 1) {
                // Replacement[1] is only meant for form == 1
                continue;
            }
            String[] repl = REPLACEMENTS[i];
            for (int j = 0; j < repl.length; j += 2) {
                result = result.replace(repl[j], repl[j + 1]);
            }
        }
        return result;
    }

    //M (1000), CM (900), D (500), CD (400), C (100), XC (90), L (50), XL (40), X (10), IX (9), V (5), IV (4) and I (1).
    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    private static final String[] ROMAN = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    private static final String[][] REPLACEMENTS = {
            { // form > 0
                    "XLV", "VL",   //  45
                    "XCV", "VC",   //  95
                    "CDL", "LD",   // 450
                    "CML", "LM",   // 950
                    "CMVC", "LMVL"  // 995
            }, { // Form == 1 only
            "CDXC", "LDXL", // 490
            "CDVC", "LDVL", // 495
            "CMXC", "LMXL", // 990
            "XCIX", "VCIV", //  99
            "XLIX", "VLIV"  //  49
    }, { // form > 1
            "XLIX", "IL",   //  49
            "XCIX", "IC",   //  99
            "CDXC", "XD",   // 490
            "CDVC", "XDV",  // 495
            "CDIC", "XDIX", // 499
            "LMVL", "XMV",  // 995
            "CMIC", "XMIX", // 999
            "CMXC", "XM"    // 990
    }, { // form > 2
            "XDV", "VD",   // 495
            "XDIX", "VDIV", // 499
            "XMV", "VM",   // 995
            "XMIX", "VMIV"  // 999
    }, { // form == 4
            "VDIV", "ID",   // 499
            "VMIV", "IM"    // 999
    }
    };
}
