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
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionContexts;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class BooleanExpressionFunctionIsEvenIsOddTest extends BooleanExpressionFunctionTestCase<BooleanExpressionFunctionIsEvenIsOdd<ExpressionFunctionContext>> {

    @Test
    public void testIsEvenNullParameterFalse() {
        assertThrows(
                NullPointerException.class,
                () -> {
                    BooleanExpressionFunctionIsEvenIsOdd.isEven().apply(Lists.of(null), this.createContext());
                });
    }

    @Test
    public void testIsOddNullParameterFalse() {
        assertThrows(
                NullPointerException.class,
                () -> {
                    BooleanExpressionFunctionIsEvenIsOdd.isOdd().apply(Lists.of(null), this.createContext());
                });
    }


    @Test
    public void testIsEvenZero() {
        this.isEvenAndCheck(0, true);
    }

    @Test
    public void testIsEvenOddInteger() {
        this.isEvenAndCheck(1, false);
    }

    @Test
    public void testIsEvenOddIgnoringDecimal() {
        this.isEvenAndCheck(3.5, false);
    }

    @Test
    public void testIsEvenEven() {
        this.isEvenAndCheck(2, true);
    }

    @Test
    public void testIsEvenEvenDecimalIgnored() {
        this.isEvenAndCheck(4.6, true);
    }

    private void isEvenAndCheck(final Number parameter,
                                final boolean expected) {
        this.isAndCheck(
                BooleanExpressionFunctionIsEvenIsOdd.isEven(),
                parameter,
                expected
        );
    }

    @Test
    public void testIsOddZero() {
        this.isOddAndCheck(0, false);
    }

    @Test
    public void testIsOddOddInteger() {
        this.isOddAndCheck(1, true);
    }

    @Test
    public void testIsOddOddIgnoringDecimal() {
        this.isOddAndCheck(3.1, true);
    }

    @Test
    public void testIsOddOddIgnoringDecimal2() {
        this.isOddAndCheck(3.5, true);
    }

    @Test
    public void testIsOddEven() {
        this.isOddAndCheck(2, false);
    }

    private void isOddAndCheck(final Number parameter,
                               final boolean expected) {
        this.isAndCheck(
                BooleanExpressionFunctionIsEvenIsOdd.isOdd(),
                parameter,
                expected
        );
    }

    private void isAndCheck(final BooleanExpressionFunctionIsEvenIsOdd<ExpressionFunctionContext> function,
                            final Number parameter,
                            final boolean expected) {
        this.applyAndCheck(
                function,
                Lists.of(ExpressionNumberKind.DOUBLE.create(parameter)),
                this.createContext(),
                expected
        );
    }

    @Test
    public void testIsEvenToString() {
        this.toStringAndCheck(
                BooleanExpressionFunctionIsEvenIsOdd.isEven(),
                "isEven"
        );
    }

    @Test
    public void testIsOddToString() {
        this.toStringAndCheck(
                BooleanExpressionFunctionIsEvenIsOdd.isOdd(),
                "isOdd"
        );
    }

    @Override
    public BooleanExpressionFunctionIsEvenIsOdd<ExpressionFunctionContext> createBiFunction() {
        return BooleanExpressionFunctionIsEvenIsOdd.isOdd();
    }

    @Override
    public ExpressionFunctionContext createContext() {
        return Cast.to(ExpressionFunctionContexts.fake());
    }

    @Override
    public Class<BooleanExpressionFunctionIsEvenIsOdd<ExpressionFunctionContext>> type() {
        return Cast.to(BooleanExpressionFunctionIsEvenIsOdd.class);
    }
}
