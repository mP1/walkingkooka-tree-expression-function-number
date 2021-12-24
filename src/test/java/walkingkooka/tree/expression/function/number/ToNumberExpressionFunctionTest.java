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
import walkingkooka.convert.ConversionException;
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionTesting;
import walkingkooka.tree.expression.function.FakeExpressionFunctionContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ToNumberExpressionFunctionTest implements ExpressionFunctionTesting<ToNumberExpressionFunction<ExpressionFunctionContext>, ExpressionNumber, ExpressionFunctionContext>,
        ClassTesting2<ToNumberExpressionFunction<ExpressionFunctionContext>> {

    final static ExpressionNumberKind KIND = ExpressionNumberKind.DEFAULT;

    @Test
    public void testZeroParametersFails() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    ToNumberExpressionFunction.instance()
                            .apply(
                                    Lists.empty(),
                                    this.createContext()
                            );
                }
        );
    }

    @Test
    public void testTwoParametersFails() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    ToNumberExpressionFunction.instance()
                            .apply(
                                    Lists.of(KIND.create(1), KIND.create(2)),
                                    this.createContext()
                            );
                }
        );
    }

    @Test
    public void testInteger() {
        this.applyAndCheck(
                Lists.of(1),
                this.createContext(),
                KIND.create(1)
        );
    }

    @Test
    public void testString() {
        this.applyAndCheck(
                Lists.of("123.5"),
                this.createContext(),
                KIND.create(123.5)
        );
    }

    @Test
    public void testStringNonNumericFails() {
        assertThrows(
                ConversionException.class,
                () -> {
                    ToNumberExpressionFunction.instance()
                            .apply(
                                    Lists.of("!fails"),
                                    this.createContext()
                            );
                }
        );
    }

    @Override
    public ToNumberExpressionFunction<ExpressionFunctionContext> createBiFunction() {
        return ToNumberExpressionFunction.instance();
    }

    @Override
    public Class<ToNumberExpressionFunction<ExpressionFunctionContext>> type() {
        return Cast.to(ToNumberExpressionFunction.class);
    }

    @Override
    public ExpressionFunctionContext createContext() {
        return new FakeExpressionFunctionContext() {
            @Override
            public ExpressionNumberKind expressionNumberKind() {
                return KIND;
            }

            @Override
            public <TT> Either<TT, String> convert(final Object value,
                                                   final Class<TT> target) {
                try {
                    final Number number = value instanceof String ?
                            new BigDecimal((String) value) :
                            (Number) value;
                    return Either.left(target.cast(KIND.create(number)));
                } catch (final Exception fail) {
                    return this.failConversion(value, target);
                }
            }
        };
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}
