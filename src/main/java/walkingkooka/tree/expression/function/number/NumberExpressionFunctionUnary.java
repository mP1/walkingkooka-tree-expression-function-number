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
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import java.util.List;
import java.util.function.BiFunction;

final class NumberExpressionFunctionUnary<C extends ExpressionFunctionContext> extends NumberExpressionFunction<C> {

    /**
     * ABSOLUTE getter.
     */
    static <C extends ExpressionFunctionContext> NumberExpressionFunctionUnary<C> absolute() {
        return Cast.to(ABSOLUTE);
    }

    /**
     * Singleton
     */
    private static final NumberExpressionFunctionUnary<?> ABSOLUTE = new NumberExpressionFunctionUnary<>(
            "abs",
            (n, c) -> n.abs(c)
    );

    /**
     * CEIL getter.
     */
    static <C extends ExpressionFunctionContext> NumberExpressionFunctionUnary<C> ceil() {
        return Cast.to(CEIL);
    }

    /**
     * CEIL Singleton
     */
    private static final NumberExpressionFunctionUnary<?> CEIL = new NumberExpressionFunctionUnary<>(
            "ceil",
            (n, c) -> n.ceil(c)
    );

    /**
     * FLOOR getter.
     */
    static <C extends ExpressionFunctionContext> NumberExpressionFunctionUnary<C> floor() {
        return Cast.to(FLOOR);
    }

    /**
     * FLOOR Singleton
     */
    private static final NumberExpressionFunctionUnary<?> FLOOR = new NumberExpressionFunctionUnary<>(
            "floor",
            (n, c) -> n.floor(c)
    );

    /**
     * ROUND getter.
     */
    static <C extends ExpressionFunctionContext> NumberExpressionFunctionUnary<C> round() {
        return Cast.to(ROUND);
    }

    /**
     * ROUND Singleton
     */
    private static final NumberExpressionFunctionUnary<?> ROUND = new NumberExpressionFunctionUnary<>(
            "round",
            (n, c) -> n.round(c)
    );

    /**
     * Private ctor
     */
    private NumberExpressionFunctionUnary(final String name,
                                          final BiFunction<ExpressionNumber, C, ExpressionNumber> function) {
        super(name);
        this.function = function;
    }

    @Override
    public ExpressionNumber apply(final List<Object> parameters,
                                  final C context) {
        this.checkParameterCount(parameters);

        return this.function.apply(
                NUMBER.getOrFail(parameters, 0),
                context
        );
    }

    private final BiFunction<ExpressionNumber, C, ExpressionNumber> function;
}
