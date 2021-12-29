/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
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


/**
 * A floor function that expects a single {@link ExpressionNumber}.
 */
final class NumberExpressionFunctionUnaryFloor<C extends ExpressionFunctionContext> extends NumberExpressionFunctionUnary<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> NumberExpressionFunctionUnaryFloor<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final NumberExpressionFunctionUnaryFloor<?> INSTANCE = new NumberExpressionFunctionUnaryFloor<>();

    /**
     * Private ctor
     */
    private NumberExpressionFunctionUnaryFloor() {
        super();
    }

    @Override
    ExpressionNumber applyExpressionNumber(final ExpressionNumber number,
                                           final ExpressionFunctionContext context) {
        return number.floor(context);
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("floor");
}

