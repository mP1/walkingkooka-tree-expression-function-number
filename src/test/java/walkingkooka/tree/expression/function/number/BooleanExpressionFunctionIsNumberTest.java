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
import walkingkooka.Either;
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.FakeExpressionFunctionContext;

public final class BooleanExpressionFunctionIsNumberTest extends BooleanExpressionFunctionTestCase<BooleanExpressionFunctionIsNumber<ExpressionFunctionContext>> {

    @Test
    public void testNullParameterFalse() {
        this.isNumberAndCheck(null, false);
    }

    @Test
    public void testStringParameterFalse() {
        this.isNumberAndCheck(
                "String123",
                false
        );
    }

    @Test
    public void testStringWithNumberParameterTrue() {
        this.isNumberAndCheck(
                "123",
                true
        );
    }

    @Test
    public void testNumberParameterTrue() {
        this.isNumberAndCheck(
                123,
                true
        );
    }

    @Test
    public void testExpressionNumberParameterTrue() {
        this.isNumberAndCheck(
                ExpressionNumberKind.BIG_DECIMAL.one(),
                true
        );
    }

    private void isNumberAndCheck(final Object parameter,
                                  final boolean expected) {
        this.applyAndCheck(
                Lists.of(parameter),
                this.createContext(),
                expected
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "isNumber");
    }

    @Override
    public BooleanExpressionFunctionIsNumber<ExpressionFunctionContext> createBiFunction() {
        return BooleanExpressionFunctionIsNumber.instance();
    }

    @Override
    public ExpressionFunctionContext createContext() {
        return new FakeExpressionFunctionContext() {
            @Override
            public <T> Either<T, String> convert(final Object value,
                                                 final Class<T> target) {
                if (value instanceof Number) {
                    return Cast.to(
                            Either.left(
                                    ExpressionNumberKind.DEFAULT.create((Number) value)
                            )
                    );
                }
                try {
                    return Cast.to(
                            Either.left(
                                    ExpressionNumberKind.DEFAULT.create(
                                            Integer.parseInt((String) value)
                                    )
                            )
                    );
                } catch (final Exception fail) {
                    return this.failConversion(
                            value,
                            target
                    );
                }
            }
        };
    }

    @Override
    public Class<BooleanExpressionFunctionIsNumber<ExpressionFunctionContext>> type() {
        return Cast.to(BooleanExpressionFunctionIsNumber.class);
    }
}
