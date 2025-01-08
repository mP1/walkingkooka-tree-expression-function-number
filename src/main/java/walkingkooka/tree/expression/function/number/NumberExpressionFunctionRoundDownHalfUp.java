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
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.math.RoundingMode;
import java.util.List;

// https://exceljet.net/excel-functions/excel-rounddown-function
// https://exceljet.net/excel-functions/excel-roundup-function
final class NumberExpressionFunctionRoundDownHalfUp<C extends ExpressionEvaluationContext> extends NumberExpressionFunction<C> {

    /**
     * ROUNDDOWN Instance getter.
     * <br>
     * https://exceljet.net/excel-functions/excel-rounddown-function
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionRoundDownHalfUp<C> roundDown() {
        return Cast.to(ROUND_DOWN);
    }

    private static final NumberExpressionFunctionRoundDownHalfUp<?> ROUND_DOWN = new NumberExpressionFunctionRoundDownHalfUp<>(
        "roundDown",
        RoundingMode.DOWN
    );

    /**
     * ROUNDHALF Instance getter.
     * <br>
     * https://exceljet.net/excel-functions/excel-round-function
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionRoundDownHalfUp<C> roundHalf() {
        return Cast.to(ROUND_HALF);
    }

    private static final NumberExpressionFunctionRoundDownHalfUp<?> ROUND_HALF = new NumberExpressionFunctionRoundDownHalfUp<>(
        "roundHalf",
        RoundingMode.HALF_UP
    );

    /**
     * ROUNDUP Instance getter.
     * <br>
     * https://exceljet.net/excel-functions/excel-roundup-function
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionRoundDownHalfUp<C> roundUp() {
        return Cast.to(ROUND_UP);
    }

    private static final NumberExpressionFunctionRoundDownHalfUp<?> ROUND_UP = new NumberExpressionFunctionRoundDownHalfUp<>(
        "roundUp",
        RoundingMode.UP
    );


    /**
     * Private ctor
     */
    private NumberExpressionFunctionRoundDownHalfUp(final String name,
                                                    final RoundingMode roundingMode) {
        super(name);
        this.roundingMode = roundingMode;
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final ExpressionFunctionParameter<ExpressionNumber> DIGITS = ExpressionFunctionParameterName.with("digits")
        .required(ExpressionNumber.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    private final List<ExpressionFunctionParameter<?>> PARAMETERS = Lists.of(
        NUMBER,
        DIGITS
    );

    @Override
    public ExpressionNumber apply(final List<Object> parameters,
                                  final C context) {
        this.checkParameterCount(parameters);

        final ExpressionNumber number = NUMBER.getOrFail(parameters, 0);
        final int digits = DIGITS.getOrFail(parameters, 1)
            .intValueExact();

        if (digits > 4) {
            throw new IllegalArgumentException("Invalid " + DIGITS.name() + " " + digits + " > 4");
        }

        // https://github.com/apache/poi/blob/bb5d321b79193b98051f435621f5044842716c4e/poi/src/main/java/org/apache/poi/ss/formula/functions/MathX.java
        // roundXXX

        return context.expressionNumberKind()
            .create(
                number.bigDecimal()
                    .setScale(digits, this.roundingMode)
                    .stripTrailingZeros()
            );
    }

    private final RoundingMode roundingMode;
}
