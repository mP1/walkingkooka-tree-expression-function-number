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
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import java.util.List;

/**
 * Counts the number of parameters given to this function.
 */
final class CountNumberExpressionFunction<C extends ExpressionFunctionContext> extends NumberExpressionFunction<ExpressionNumber, C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> CountNumberExpressionFunction<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final CountNumberExpressionFunction INSTANCE = new CountNumberExpressionFunction();

    private CountNumberExpressionFunction() {
        super();
    }

    @Override
    public ExpressionNumber apply(final List<Object> parameters,
                                  final C context) {
        return context.expressionNumberKind().create(parameters.size());
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("count");
}
