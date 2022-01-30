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
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

public final class StringExpressionFunctionRomanTest extends StringExpressionFunctionTestCase<StringExpressionFunctionRoman<ExpressionFunctionContext>, String> {

    @Test
    public void test4() {
        this.romanAndCheck(
                4,
                "IV"
        );
    }

    @Test
    public void test99() {
        this.romanAndCheck(
                99,
                "XCIX"
        );
    }

    @Test
    public void test99DecimalIgnored() {
        this.romanAndCheck(
                99.5,
                "XCIX"
        );
    }

    @Test
    public void test1999() {
        this.romanAndCheck(
                1999,
                "MCMXCIX");
    }

    @Test
    public void test2022() {
        this.romanAndCheck(
                2022,
                "MMXXII");
    }


    private void romanAndCheck(final Number value,
                               final String roman) {
        this.applyAndCheck2(
                Lists.of(
                        KIND.create(value)
                ),
                roman
        );
    }

    @Test
    public void test1999Type0() {
        this.romanAndCheck(
                1999,
                0,
                "MCMXCIX");
    }

    @Test
    public void test1999Type1() {
        this.romanAndCheck(
                1999,
                1,
                "MLMVLIV");
    }

    @Test
    public void test1999Type2() {
        this.romanAndCheck(
                1999,
                2,
                "MXMIX");
    }

    @Test
    public void test1999Type3() {
        this.romanAndCheck(
                1999,
                3,
                "MVMIV");
    }

    @Test
    public void test1999Type4() {
        this.romanAndCheck(
                1999,
                4,
                "MIM");
    }

    private void romanAndCheck(final int value,
                               final int type,
                               final String roman) {
        this.applyAndCheck2(
                Lists.of(
                        KIND.create(value),
                        KIND.create(type)
                ),
                roman
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createBiFunction(),
                "roman"
        );
    }

    @Override
    public StringExpressionFunctionRoman<ExpressionFunctionContext> createBiFunction() {
        return StringExpressionFunctionRoman.instance();
    }

    @Override
    public Class<StringExpressionFunctionRoman<ExpressionFunctionContext>> type() {
        return Cast.to(StringExpressionFunctionRoman.class);
    }
}
