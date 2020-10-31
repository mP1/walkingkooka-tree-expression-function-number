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

import java.util.function.Consumer;

/**
 * Collection of static factory methods for numerous {@link ExpressionFunction}.
 */
public final class NumberExpressionFunctions implements PublicStaticHelper {

    /**
     * Visit all {@link ExpressionFunction functions}.
     */
    public static void visit(final Consumer<ExpressionFunction<?>> consumer) {
        Lists.of(abs(),
                ceil(),
                count(),
                floor(),
                number(),
                round())
                .forEach(consumer);
    }

    /**
     * {@see AbsoluteNumberExpressionFunction}
     */
    public static ExpressionFunction<ExpressionNumber> abs() {
        return AbsoluteNumberExpressionFunction.INSTANCE;
    }

    /**
     * {@see CeilNumberExpressionFunction}
     */
    public static ExpressionFunction<ExpressionNumber> ceil() {
        return CeilNumberExpressionFunction.INSTANCE;
    }

    /**
     * {@see CountNumberExpressionFunction}
     */
    public static ExpressionFunction<ExpressionNumber> count() {
        return CountNumberExpressionFunction.INSTANCE;
    }

    /**
     * {@see FloorNumberExpressionFunction}
     */
    public static ExpressionFunction<ExpressionNumber> floor() {
        return FloorNumberExpressionFunction.INSTANCE;
    }

    /**
     * {@see NumberExpressionFunction}
     */
    public static ExpressionFunction<ExpressionNumber> number() {
        return ToNumberExpressionFunction.INSTANCE;
    }

    /**
     * {@see RoundNumberExpressionFunction}
     */
    public static ExpressionFunction<ExpressionNumber> round() {
        return RoundNumberExpressionFunction.INSTANCE;
    }

    /**
     * Stops creation
     */
    private NumberExpressionFunctions() {
        throw new UnsupportedOperationException();
    }
}
