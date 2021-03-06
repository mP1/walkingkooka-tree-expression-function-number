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
import walkingkooka.convert.ConversionException;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ToNumberExpressionFunctionTest extends UnaryNumberExpressionFunctionTestCase<ToNumberExpressionFunction<ExpressionFunctionContext>> {

    @Test
    public void testZeroParametersFails() {
        assertThrows(IllegalArgumentException.class, this::apply2);
    }

    @Test
    public void testTwoParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1", "b2"));
    }

    @Test
    public void testInteger() {
        this.applyAndCheck3(123, 123);
    }

    @Test
    public void testString() {
        this.applyAndCheck3("123", 123);
    }

    @Test
    public void testStringNonNumericFails() {
        assertThrows(ConversionException.class, () -> this.apply2("abc"));
    }

    @Override
    String functionToString() {
        return "number";
    }

    @Override
    public ToNumberExpressionFunction<ExpressionFunctionContext> createBiFunction() {
        return ToNumberExpressionFunction.instance();
    }

    @Override
    public Class<ToNumberExpressionFunction<ExpressionFunctionContext>> type() {
        return Cast.to(ToNumberExpressionFunction.class);
    }
}
