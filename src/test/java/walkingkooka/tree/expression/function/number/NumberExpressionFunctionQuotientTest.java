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
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

public final class NumberExpressionFunctionQuotientTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionQuotient<ExpressionFunctionContext>> {

    @Test
    public void test3And5() {
        this.quotientAndCheck(
                3,
                5,
                0
        );
    }

    @Test
    public void test25And5() {
        this.quotientAndCheck(
                25,
                5,
                5
        );
    }

    @Test
    public void test10And3() {
        this.quotientAndCheck(
                10,
                3,
                3
        );
    }

    @Test
    public void test61And10() {
        this.quotientAndCheck(
                61,
                10,
                6
        );
    }

    private void quotientAndCheck(final double num,
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
                "quotient"
        );
    }

    @Override
    public NumberExpressionFunctionQuotient<ExpressionFunctionContext> createBiFunction() {
        return NumberExpressionFunctionQuotient.instance();
    }

    @Override
    public Class<NumberExpressionFunctionQuotient<ExpressionFunctionContext>> type() {
        return Cast.to(NumberExpressionFunctionQuotient.class);
    }
}
