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

import walkingkooka.reflect.PublicStaticHelper;
import walkingkooka.tree.expression.function.ExpressionFunction;

/**
 * Collection of static factory methods for numerous {@link ExpressionFunction}.
 */
public final class NumberExpressionFunctions implements PublicStaticHelper {

    /**
     * {@see NumberExpressionFunction2Absolute}
     */
    public static ExpressionFunction<Number> abs() {
        return NumberExpressionFunction2Absolute.INSTANCE;
    }

    /**
     * {@see NumberExpressionFunction2Ceil}
     */
    public static ExpressionFunction<Number> ceil() {
        return NumberExpressionFunction2Ceil.INSTANCE;
    }

    /**
     * {@see NumberExpressionFunction2Floor}
     */
    public static ExpressionFunction<Number> floor() {
        return NumberExpressionFunction2Floor.INSTANCE;
    }

    /**
     * {@see NumberExpressionFunction}
     */
    public static ExpressionFunction<Number> number() {
        return NumberExpressionFunction.INSTANCE;
    }

    /**
     * {@see NumberExpressionFunction2Round}
     */
    public static ExpressionFunction<Number> round() {
        return NumberExpressionFunction2Round.INSTANCE;
    }

    /**
     * Stops creation
     */
    private NumberExpressionFunctions() {
        throw new UnsupportedOperationException();
    }
}