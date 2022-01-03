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

abstract class NumberExpressionFunctionUnary<C extends ExpressionFunctionContext> extends NumberExpressionFunction<C> {

    /**
     * Package private ctor
     */
    NumberExpressionFunctionUnary(final String name) {
        super(name);
    }

    @Override
    public final ExpressionNumber apply(final List<Object> parameters,
                                        final C context) {
        this.checkOnlyRequiredParameters(parameters);

        return applyExpressionNumber(
                NUMBER.getOrFail(parameters, 0),
                context
        );
    }

    abstract ExpressionNumber applyExpressionNumber(final ExpressionNumber number,
                                                    final ExpressionFunctionContext context);
}
