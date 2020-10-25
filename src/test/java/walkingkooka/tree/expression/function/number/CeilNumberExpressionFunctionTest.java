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

public final class CeilNumberExpressionFunctionTest extends UnaryNumberExpressionFunctionTestCase<CeilNumberExpressionFunction> {

    // Double........................................................................................................

    @Test
    public void testDoubleRoundUp() {
        this.applyAndCheck3(1.25, 2);
    }

    @Test
    public void testDoubleRoundUp2() {
        this.applyAndCheck3(1.5, 2);
    }

    @Test
    public void testDoubleNoRounding() {
        this.applyAndCheck3(99);
    }

    // helper...........................................................................................................

    @Override
    public CeilNumberExpressionFunction createBiFunction() {
        return CeilNumberExpressionFunction.INSTANCE;
    }

    @Override
    public Class<CeilNumberExpressionFunction> type() {
        return CeilNumberExpressionFunction.class;
    }

    @Override
    String functionToString() {
        return "ceil";
    }
}
