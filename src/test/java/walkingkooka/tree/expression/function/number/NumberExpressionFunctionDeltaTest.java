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

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class NumberExpressionFunctionDeltaTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionDelta<ExpressionEvaluationContext>> {

    @Test
    public void testNonNumberFails() {
        assertThrows(
                ClassCastException.class,
                () -> this.apply2("Not-a-number")
        );
    }

    @Test
    public void testNonNumberParameter2Fails() {
        assertThrows(
                ClassCastException.class,
                () -> this.apply2(
                        KIND.create(123),
                        "Not-a-number"
                )
        );
    }

    @Test
    public void testNonZero() {
        this.deltaAndCheck(1, 0);
    }

    @Test
    public void testZero() {
        this.deltaAndCheck(0, 1);
    }

    private void deltaAndCheck(final Number number1,
                               final Number expected) {
        this.applyAndCheck2(
                Lists.of(KIND.create(number1)),
                KIND.create(expected)
        );
    }

    @Test
    public void testZeroAndZero() {
        this.deltaAndCheck(0, 0, 1);
    }

    @Test
    public void testSame() {
        this.deltaAndCheck(2.5, 2.5, 1);
    }

    @Test
    public void testDifferent() {
        this.deltaAndCheck(1, 0, 0);
    }

    @Test
    public void testDifferent2() {
        this.deltaAndCheck(2, 1, 0);
    }

    private void deltaAndCheck(final Number number1,
                               final Number number2,
                               final Number expected) {
        this.applyAndCheck2(
                Lists.of(
                        KIND.create(number1),
                        KIND.create(number2)
                ),
                KIND.create(expected)
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createBiFunction(),
                "delta"
        );
    }

    @Override
    public NumberExpressionFunctionDelta<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionDelta.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 2;
    }

    @Override
    public Class<NumberExpressionFunctionDelta<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionDelta.class);
    }
}
