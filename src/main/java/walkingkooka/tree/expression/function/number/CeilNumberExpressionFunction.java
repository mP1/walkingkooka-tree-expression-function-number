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

import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

/**
 * A ceil function that expects a single {@link ExpressionNumber}.
 */
final class CeilNumberExpressionFunction extends UnaryNumberExpressionFunction {

    /**
     * Singleton
     */
    static final CeilNumberExpressionFunction INSTANCE = new CeilNumberExpressionFunction();

    /**
     * Private ctor
     */
    private CeilNumberExpressionFunction() {
        super();
    }

    @Override
    ExpressionNumber applyExpressionNumber(final ExpressionNumber number,
                                           final ExpressionFunctionContext context) {
        return number.ceil(context);
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("ceil");
}
