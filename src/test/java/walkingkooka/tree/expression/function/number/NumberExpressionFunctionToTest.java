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
import walkingkooka.convert.ConversionException;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class NumberExpressionFunctionToTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionTo<ExpressionFunctionContext>> {

    final static ExpressionNumberKind KIND = ExpressionNumberKind.DEFAULT;

    @Test
    public void testZeroParametersFails() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    NumberExpressionFunctionTo.instance()
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
                    NumberExpressionFunctionTo.instance()
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
                    NumberExpressionFunctionTo.instance()
                            .apply(
                                    Lists.of("!fails"),
                                    this.createContext()
                            );
                }
        );
    }

    @Override
    public NumberExpressionFunctionTo<ExpressionFunctionContext> createBiFunction() {
        return NumberExpressionFunctionTo.instance();
    }

    @Override
    public Class<NumberExpressionFunctionTo<ExpressionFunctionContext>> type() {
        return Cast.to(NumberExpressionFunctionTo.class);
    }
}
