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
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.util.List;

// https://support.google.com/docs/answer/3093296?hl=en&ref_topic=3105471
final class BooleanExpressionFunctionIsNumber<C extends ExpressionFunctionContext> extends BooleanExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> BooleanExpressionFunctionIsNumber<C> instance() {
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
        return ExpressionFunctionParameter.VALUE.getOrFail(parameters, 0) instanceof Number;
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(ExpressionFunctionParameter.VALUE);
}
