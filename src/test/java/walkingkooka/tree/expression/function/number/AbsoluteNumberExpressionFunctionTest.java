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

public final class AbsoluteNumberExpressionFunctionTest extends UnaryNumberExpressionFunctionTestCase<AbsoluteNumberExpressionFunction<ExpressionFunctionContext>> {

    // Double...........................................................................................................

    @Test
    public void testDoubleNegative() {
        this.applyAndCheck3(-2.0, 2.0);
    }

    @Test
    public void testDoubleZero() {
        this.applyAndCheck3(0.0);
    }

    @Test
    public void testDoublePositive() {
        this.applyAndCheck3(3.0);
    }

    // Long.............................................................................................................

    @Test
    public void testLongNegative() {
        this.applyAndCheck3(-2L, 2L);
    }

    @Test
    public void testLongZero() {
        this.applyAndCheck3(0L);
    }

    @Test
    public void testLongPositive() {
        this.applyAndCheck3(3L);
    }

    // Integer.............................................................................................................

    @Test
    public void testIntegerNegative() {
        this.applyAndCheck3(-34, 34.0);
    }

    // helper...........................................................................................................

    @Override
    public AbsoluteNumberExpressionFunction<ExpressionFunctionContext> createBiFunction() {
        return AbsoluteNumberExpressionFunction.instance();
    }

    @Override
    public Class<AbsoluteNumberExpressionFunction<ExpressionFunctionContext>> type() {
        return Cast.to(AbsoluteNumberExpressionFunction.class);
    }

    @Override
    String functionToString() {
        return "abs";
    }
}
