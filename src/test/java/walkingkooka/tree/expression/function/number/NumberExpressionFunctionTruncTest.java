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

public final class NumberExpressionFunctionTruncTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionTrunc<ExpressionEvaluationContext>> {

    @Test
    public void testZeroDigits() {
        this.truncAndCheck(
                1.2,
                1
        );
    }

    @Test
    public void testZeroDigits2() {
        this.truncAndCheck(
                -1.2,
                -1
        );
    }

    private void truncAndCheck(final Number value,
                               final Number expected) {
        this.applyAndCheck2(
                Lists.of(
                        KIND.create(value)
                ),
                KIND.create(expected)
        );
    }

    @Test
    public void testOneDigit() {
        this.truncAndCheck(
                1.65,
                1,
                1.6
        );
    }

    @Test
    public void testTwoDigit() {
        this.truncAndCheck(
                1.234,
                2,
                1.23
        );
    }

    @Test
    public void testMinus0Digits() {
        this.truncAndCheck(
                999.99,
                0,
                999
        );
    }

    @Test
    public void testMinusOneDigit() {
        this.truncAndCheck(
                999.99,
                -1,
                990
        );
    }

    @Test
    public void testMinusTwoDigits() {
        this.truncAndCheck(
                999.99,
                -2,
                900
        );
    }

    private void truncAndCheck(final Number value,
                               final Number digits,
                               final Number expected) {
        this.applyAndCheck2(
                Lists.of(
                        KIND.create(value),
                        KIND.create(digits)
                ),
                KIND.create(expected)
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createBiFunction(),
                "trunc"
        );
    }

    @Override
    public NumberExpressionFunctionTrunc<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionTrunc.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 2;
    }

    @Override
    public Class<NumberExpressionFunctionTrunc<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionTrunc.class);
    }
}
