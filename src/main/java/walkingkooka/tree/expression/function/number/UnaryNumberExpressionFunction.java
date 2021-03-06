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

import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import java.util.List;

abstract class UnaryNumberExpressionFunction<C extends ExpressionFunctionContext> extends NumberExpressionFunction<ExpressionNumber, C> {

    /**
     * Package private ctor
     */
    UnaryNumberExpressionFunction() {
        super();
    }

    @Override
    public final ExpressionNumber apply(final List<Object> parameters,
                                        final C context) {
        this.checkParameterCount(parameters, 1);
        return applyExpressionNumber(this.expressionNumber(parameters, 0, context), context);
    }

    abstract ExpressionNumber applyExpressionNumber(final ExpressionNumber number,
                                                    final ExpressionFunctionContext context);
}
