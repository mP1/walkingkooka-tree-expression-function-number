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

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionEvaluationContext;

public final class NumberExpressionFunctionProductTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionProduct<ExpressionEvaluationContext>> {

    @Test
    public void test3And5() {
        this.productAndCheck(
            3,
            5,
            3 * 5
        );
    }

    @Test
    public void test25And5() {
        this.productAndCheck(
            25,
            5,
            25 * 5
        );
    }

    @Test
    public void test10And3() {
        this.productAndCheck(
            10,
            3,
            10 * 3
        );
    }

    private void productAndCheck(final double num,
                                 final double denom,
                                 final double expected) {
        this.applyAndCheck2(
            Lists.of(
                KIND.create(num),
                KIND.create(denom)
            ),
            KIND.create(expected)
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createBiFunction(),
            "product"
        );
    }

    @Override
    public NumberExpressionFunctionProduct<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionProduct.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 2;
    }

    @Override
    public Class<NumberExpressionFunctionProduct<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionProduct.class);
    }
}
