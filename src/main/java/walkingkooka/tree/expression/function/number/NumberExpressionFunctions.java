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

import walkingkooka.collect.list.Lists;
import walkingkooka.reflect.PublicStaticHelper;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import java.util.function.Consumer;

/**
 * Collection of static factory methods for numerous {@link ExpressionFunction}.
 */
public final class NumberExpressionFunctions implements PublicStaticHelper {

    /**
     * Visit all {@link ExpressionFunction functions}.
     */
    public static void visit(final Consumer<ExpressionFunction<?, ?>> consumer) {
        Lists.of(abs(),
                average(),
                ceil(),
                count(),
                floor(),
                max(),
                number(),
                round(),
                sum())
                .forEach(consumer);
    }

    /**
     * {@see AbsoluteNumberExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> abs() {
        return AbsoluteNumberExpressionFunction.instance();
    }

    /**
     * {@see AverageNumberExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> average() {
        return AverageNumberExpressionFunction.instance();
    }

    /**
     * {@see CeilNumberExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> ceil() {
        return CeilNumberExpressionFunction.instance();
    }

    /**
     * {@see CountNumberExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> count() {
        return CountNumberExpressionFunction.instance();
    }

    /**
     * {@see FloorNumberExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> floor() {
        return FloorNumberExpressionFunction.instance();
    }

    /**
     * {@see MaxNumberExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> max() {
        return MaxNumberExpressionFunction.instance();
    }

    /**
     * {@see NumberExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> number() {
        return ToNumberExpressionFunction.instance();
    }

    /**
     * {@see RoundNumberExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> round() {
        return RoundNumberExpressionFunction.instance();
    }

    /**
     * {@see SumNumberExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> sum() {
        return SumNumberExpressionFunction.instance();
    }

    /**
     * Stops creation
     */
    private NumberExpressionFunctions() {
        throw new UnsupportedOperationException();
    }
}
