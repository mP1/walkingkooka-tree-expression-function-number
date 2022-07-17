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
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * Returns the quotient for the given parameters.
 */
// https://exceljet.net/excel-functions/excel-mod-function
final class NumberExpressionFunctionMod<C extends ExpressionEvaluationContext> extends NumberExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionMod<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final NumberExpressionFunctionMod<?> INSTANCE = new NumberExpressionFunctionMod<>();

    private NumberExpressionFunctionMod() {
        super("mod");
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<ExpressionNumber> NUMERATOR = ExpressionFunctionParameterName.with("numerator")
            .required(ExpressionNumber.class);

    private final static ExpressionFunctionParameter<ExpressionNumber> DENOMINATOR = ExpressionFunctionParameterName.with("denominator")
            .required(ExpressionNumber.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = Lists.of(
            NUMERATOR,
            DENOMINATOR
    );

    // https://github.com/apache/poi/blob/bb5d321b79193b98051f435621f5044842716c4e/poi/src/main/java/org/apache/poi/ss/formula/functions/MathX.java

    @Override
    public ExpressionNumber apply(final List<Object> parameters,
                                  final C context) {
        this.checkParameterCount(parameters);

        final ExpressionNumber num = NUMERATOR.getOrFail(parameters, 0);
        final ExpressionNumber denom = DENOMINATOR.getOrFail(parameters, 1);

        ExpressionNumber mod = num.modulo(denom, context);
        if (num.sign() != denom.sign()) {
            // return ((n % d) + d) % d;
            mod = mod.add(denom, context)
                    .modulo(denom, context);
        }

        return mod;
    }
}
