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
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

public final class FloorNumberExpressionFunctionTest extends UnaryNumberExpressionFunctionTestCase<FloorNumberExpressionFunction<ExpressionFunctionContext>> {

    // Double...........................................................................................................

    @Test
    public void testDouble() {
        this.applyAndCheck3(1.25, 1.0);
    }

    @Test
    public void testDouble2() {
        this.applyAndCheck3(1.5, 1.0);
    }

    @Test
    public void testDouble3() {
        this.applyAndCheck3(1.0);
    }

    // Long.............................................................................................................

    @Test
    public void testLong() {
        this.applyAndCheck3(-2L);
    }

    // Integer.............................................................................................................

    @Test
    public void testIntegerNegative() {
        this.applyAndCheck3(-34, -34);
    }

    // helper...........................................................................................................

    @Override
    public FloorNumberExpressionFunction<ExpressionFunctionContext> createBiFunction() {
        return FloorNumberExpressionFunction.instance();
    }

    @Override
    public Class<FloorNumberExpressionFunction<ExpressionFunctionContext>> type() {
        return Cast.to(FloorNumberExpressionFunction.class);
    }

    @Override
    String functionToString() {
        return "floor";
    }
}
