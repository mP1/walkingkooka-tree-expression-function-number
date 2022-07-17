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
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.util.List;

/**
 * A {@link walkingkooka.tree.expression.function.ExpressionFunction} that requires a single parameter, and return true
 * if it is a {@link ExpressionNumber} or can be converted to a {@link ExpressionNumber}.
 *
 * @param <C>
 */
// https://support.google.com/docs/answer/3093296?hl=en&ref_topic=3105471
final class BooleanExpressionFunctionIsNumber<C extends ExpressionEvaluationContext> extends BooleanExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionIsNumber<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionIsNumber<?> INSTANCE = new BooleanExpressionFunctionIsNumber<>();

    private BooleanExpressionFunctionIsNumber() {
        super("isNumber");
    }

    @Override
    public Boolean apply(final List<Object> parameters,
                         final C context) {
        this.checkParameterCount(parameters);

        return context.convert(
                ExpressionFunctionParameter.VALUE.getOrFail(parameters, 0),
                ExpressionNumber.class
        ).isLeft();
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(ExpressionFunctionParameter.VALUE);
}
