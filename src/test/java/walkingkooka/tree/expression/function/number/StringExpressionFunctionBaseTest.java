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

public final class StringExpressionFunctionBaseTest extends StringExpressionFunctionTestCase<StringExpressionFunctionBase<ExpressionFunctionContext>, String> {

    @Test
    public void testBase2() {
        this.baseAndCheck(
                15,
                2,
                "1111"
        );
    }

    @Test
    public void testBase3() {
        this.baseAndCheck(
                15,
                3,
                "120"
        );
    }

    @Test
    public void testBase16() {
        this.baseAndCheck(
                255,
                16,
                "FF"
        );
    }

    private void baseAndCheck(final Number number,
                              final Number base,
                              final String text) {
        this.applyAndCheck2(
                Lists.of(
                        KIND.create(number),
                        KIND.create(base)
                ),
                text
        );
    }

    @Test
    public void testBase2MinLength6() {
        this.baseAndCheck(
                15,
                2,
                6,
                "001111"
        );
    }

    @Test
    public void testBase3MinLength6() {
        this.baseAndCheck(
                15,
                3,
                6,
                "000120"
        );
    }

    @Test
    public void testBase16MinLengthLess() {
        this.baseAndCheck(
                255,
                16,
                1,
                "FF"
        );
    }

    @Test
    public void testBase16MinLength6() {
        this.baseAndCheck(
                255,
                16,
                6,
                "0000FF"
        );
    }

    private void baseAndCheck(final Number number,
                              final Number base,
                              final Number minLength,
                              final String text) {
        this.applyAndCheck2(
                Lists.of(
                        KIND.create(number),
                        KIND.create(base),
                        KIND.create(minLength)
                ),
                text
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createBiFunction(),
                "base"
        );
    }

    @Override
    public StringExpressionFunctionBase<ExpressionFunctionContext> createBiFunction() {
        return StringExpressionFunctionBase.instance();
    }

    @Override
    public Class<StringExpressionFunctionBase<ExpressionFunctionContext>> type() {
        return Cast.to(StringExpressionFunctionBase.class);
    }
}
