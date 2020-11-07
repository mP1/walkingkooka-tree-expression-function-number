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
 * Returns the max value for one or more numbers.
 */
final class MaxNumberExpressionFunction<C extends ExpressionFunctionContext> extends NumberExpressionFunction<ExpressionNumber, C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> MaxNumberExpressionFunction<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final MaxNumberExpressionFunction INSTANCE = new MaxNumberExpressionFunction();

    private MaxNumberExpressionFunction() {
        super();
    }

    @Override
    public ExpressionNumber apply(final List<Object> parameters,
                                  final C context) {
        if (parameters.isEmpty()) {
            throw new IllegalArgumentException("Expected at least one number");
        }
        return parameters.stream()
                .skip(1)
                .map(p -> context.convertOrFail(p, ExpressionNumber.class))
                .reduce(context.convertOrFail(parameters.get(0), ExpressionNumber.class), (subTotal, p) -> subTotal.max(p));
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("max");
}
