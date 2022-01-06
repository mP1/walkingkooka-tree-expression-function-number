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
import walkingkooka.tree.expression.ExpressionPurityContext;
import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.util.List;

// https://support.google.com/docs/answer/3093296?hl=en&ref_topic=3105471
final class IsNumberExpressionFunction<C extends ExpressionFunctionContext> implements ExpressionFunction<Boolean, C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> IsNumberExpressionFunction<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final IsNumberExpressionFunction<?> INSTANCE = new IsNumberExpressionFunction<>();


    private IsNumberExpressionFunction() {
        super();
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private FunctionExpressionName NAME = FunctionExpressionName.with("isNumber");

    @Override
    public Boolean apply(final List<Object> parameters,
                         final C context) {
        this.checkOnlyRequiredParameters(parameters);
        return ExpressionFunctionParameter.VALUE.getOrFail(parameters, 0) instanceof Number;
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(ExpressionFunctionParameter.VALUE);

    @Override
    public boolean lsLastParameterVariable() {
        return false;
    }

    @Override
    public Class<Boolean> returnType() {
        return Boolean.class;
    }

    /**
     * All number functions are pure. Does not assume anything about any parameters.
     */
    @Override
    public boolean isPure(final ExpressionPurityContext context) {
        return true;
    }

    @Override
    public boolean resolveReferences() {
        return true;
    }

    @Override
    public String toString() {
        return this.name().toString();
    }
}
