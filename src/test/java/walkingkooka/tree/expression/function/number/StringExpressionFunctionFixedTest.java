/*
 * Copyright 2022 Miroslav Pokorny (github.com/mP1)
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

public final class StringExpressionFunctionFixedTest extends StringExpressionFunctionTestCase<StringExpressionFunctionFixed<ExpressionEvaluationContext>, String> {

    @Test
    public void testOnlyNumber() {
        this.fixedAndCheck(
                4,
                "4d00"
        );
    }

    @Test
    public void testNegativeOnlyNumber() {
        this.fixedAndCheck(
                -4,
                "n4d00"
        );
    }

    @Test
    public void testOnlyNumberDecimal() {
        this.fixedAndCheck(
                4.5,
                "4d50"
        );
    }

    @Test
    public void testOnlyNumberDecimal3() {
        this.fixedAndCheck(
                4.123,
                "4d12"
        );
    }

    private void fixedAndCheck(final Number value,
                               final String fixed) {
        this.applyAndCheck2(
                Lists.of(
                        KIND.create(value)
                ),
                fixed
        );
    }

    @Test
    public void test1_3() {
        this.fixedAndCheck(
                1,
                3,
                "1d000"
        );
    }

    @Test
    public void test1000_2() {
        this.fixedAndCheck(
                1000,
                2,
                "1g000d00"
        );
    }

    @Test
    public void test1000_3() {
        this.fixedAndCheck(
                1000,
                3,
                "1g000d000"
        );
    }

    @Test
    public void test1000_1() {
        this.fixedAndCheck(
                1000,
                1,
                "1g000d0"
        );
    }

    @Test
    public void test1000_1Rounded() {
        this.fixedAndCheck(
                1000.45,
                1,
                "1g000d5"
        );
    }

    private void fixedAndCheck(final Number value,
                               final Number decimals,
                               final String fixed) {
        this.applyAndCheck2(
                Lists.of(
                        KIND.create(value),
                        KIND.create(decimals)
                ),
                fixed
        );
    }

    @Test
    public void test1000_1_False() {
        this.fixedAndCheck(
                1000.45,
                1,
                false,
                "1g000d5"
        );
    }

    @Test
    public void test1000_1_True() {
        this.fixedAndCheck(
                1000.45,
                1,
                true,
                "1000d5"
        );
    }

    private void fixedAndCheck(final Number value,
                               final Number decimals,
                               final boolean suppressSeparators,
                               final String fixed) {
        this.applyAndCheck2(
                Lists.of(
                        KIND.create(value),
                        KIND.create(decimals),
                        suppressSeparators
                ),
                fixed
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createBiFunction(),
                "fixed"
        );
    }

    @Override
    public StringExpressionFunctionFixed<ExpressionEvaluationContext> createBiFunction() {
        return StringExpressionFunctionFixed.instance();
    }

    @Override
    public Class<StringExpressionFunctionFixed<ExpressionEvaluationContext>> type() {
        return Cast.to(StringExpressionFunctionFixed.class);
    }
}
