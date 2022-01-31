/*
 * Copyright 2020 Miroslav Pokorny (github.com/mP1)
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

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class NumberExpressionFunctionUnaryTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionUnary<ExpressionFunctionContext>> {

    // abs........................................................................................................

    @Test
    public void testAbsoluteNegative() {
        this.absoluteAndCheck(-2.0, 2.0);
    }

    @Test
    public void testAbsoluteZero() {
        this.absoluteAndCheck(0.0);
    }

    @Test
    public void testAbsolutePositive() {
        this.absoluteAndCheck(3.0);
    }

    private void absoluteAndCheck(final double value) {
        this.absoluteAndCheck(value, value);
    }

    private void absoluteAndCheck(final double value,
                                  final double expected) {
        this.applyAndCheck3(
                NumberExpressionFunctionUnary.absolute(),
                value,
                expected
        );
    }

    // ceil............................................................................................................

    @Test
    public void testCeilRoundUp() {
        this.ceilAndCheck(1.25, 2);
    }

    @Test
    public void testCeilRoundUp2() {
        this.ceilAndCheck(1.5, 2);
    }

    @Test
    public void testCeilNoRounding() {
        this.ceilAndCheck(99);
    }

    private void ceilAndCheck(final double value) {
        this.ceilAndCheck(value, value);
    }

    private void ceilAndCheck(final double value,
                              final double expected) {
        this.applyAndCheck3(
                NumberExpressionFunctionUnary.ceil(),
                value,
                expected
        );
    }

    // even............................................................................................................

    @Test
    public void testEvenZero() {
        this.evenAndCheck(0);
    }

    @Test
    public void testEven4() {
        this.evenAndCheck(4);
    }

    @Test
    public void testEven6Half() {
        this.evenAndCheck(6.5, 8);
    }

    @Test
    public void testEven1() {
        this.evenAndCheck(1, 2);
    }

    @Test
    public void testEvenMinus1() {
        this.evenAndCheck(-1, -2);
    }

    private void evenAndCheck(final double value) {
        this.evenAndCheck(value, value);
    }

    private void evenAndCheck(final double value,
                              final double expected) {
        this.applyAndCheck3(
                NumberExpressionFunctionUnary.even(),
                value,
                expected
        );
    }

    // floor............................................................................................................

    @Test
    public void testFloorRoundUp() {
        this.floorAndCheck(1.25, 1.0);
    }

    @Test
    public void testFloorRoundUp2() {
        this.floorAndCheck(1.5, 1.0);
    }

    @Test
    public void testFloorNoRounding() {
        this.floorAndCheck(1.0);
    }

    private void floorAndCheck(final double value) {
        this.floorAndCheck(value, value);
    }

    private void floorAndCheck(final double value,
                               final double expected) {
        this.applyAndCheck3(
                NumberExpressionFunctionUnary.floor(),
                value,
                expected
        );
    }

    // ln............................................................................................................

    @Test
    public void testLnNegativeFails() {
        assertThrows(
                IllegalArgumentException.class,
                () -> KIND.create(-1)
                        .ln(this.createContext())
        );
    }

    @Test
    public void testLn1() {
        this.lnAndCheck(1, 0);
    }

    @Test
    public void testLn15() {
        this.lnAndCheck(1.5, 0.4054651081081644);
    }

    @Test
    public void testLn5() {
        this.lnAndCheck(5, 1.6094379124341003);
    }

    private void lnAndCheck(final double value,
                            final double expected) {
        this.applyAndCheck3(
                NumberExpressionFunctionUnary.ln(),
                value,
                expected
        );
    }

    // odd............................................................................................................

    @Test
    public void testOddZero() {
        this.oddAndCheck(1);
    }

    @Test
    public void testOdd1() {
        this.oddAndCheck(1);
    }

    @Test
    public void testOdd3() {
        this.oddAndCheck(3, 3);
    }

    @Test
    public void testOdd4() {
        this.oddAndCheck(4, 5);
    }

    @Test
    public void testOdd3Half() {
        this.oddAndCheck(3.5, 5);
    }

    @Test
    public void testOddMinus2() {
        this.oddAndCheck(-2, -3);
    }

    private void oddAndCheck(final double value) {
        this.oddAndCheck(value, value);
    }

    private void oddAndCheck(final double value,
                             final double expected) {
        this.applyAndCheck3(
                NumberExpressionFunctionUnary.odd(),
                value,
                expected
        );
    }

    // round............................................................................................................

    @Test
    public void testRoundDoubleDown() {
        this.roundAndCheck(1.25, 1L);
    }

    @Test
    public void testRoundDoubleUp() {
        this.roundAndCheck(1.5, 2L);
    }

    @Test
    public void testRoundLong() {
        this.roundAndCheck(-2L);
    }

    @Test
    public void testRoundIntegerNegative() {
        this.roundAndCheck(-34.2, -34);
    }

    private void roundAndCheck(final Number value) {
        this.roundAndCheck(value, value);
    }

    private void roundAndCheck(final Number value,
                               final Number expected) {
        this.applyAndCheck3(
                NumberExpressionFunctionUnary.round(),
                value,
                expected
        );
    }

    // sign............................................................................................................

    @Test
    public void testSignZero() {
        this.signAndCheck(0, 0);
    }

    @Test
    public void testSignNegative() {
        this.signAndCheck(-10, -1);
    }

    @Test
    public void testSignPositive() {
        this.signAndCheck(+20, 1);
    }

    private void signAndCheck(final double value,
                              final double expected) {
        this.applyAndCheck3(
                NumberExpressionFunctionUnary.sign(),
                value,
                expected
        );
    }

    // sqrt............................................................................................................

    @Test
    public void testSqrtZero() {
        this.sqrtAndCheck(0, 0);
    }

    @Test
    public void testSqrt1() {
        this.sqrtAndCheck(1, 1);
    }

    @Test
    public void testSqrt9() {
        this.sqrtAndCheck(9, 3);
    }

    private void sqrtAndCheck(final double value,
                              final double expected) {
        this.applyAndCheck3(
                NumberExpressionFunctionUnary.sqrt(),
                value,
                expected
        );
    }

    // helper...........................................................................................................

    private void applyAndCheck3(final NumberExpressionFunctionUnary<ExpressionFunctionContext> function,
                                final Number value,
                                final Number expected) {
        this.applyAndCheck(
                function,
                Lists.of(
                        KIND.create(value)
                ),
                this.createContext(),
                KIND.create(expected)
        );
    }

    // toString........................................................................................................

    @Test
    public void testToStringAbs() {
        this.toStringAndCheck(
                NumberExpressionFunctionUnary.absolute(),
                "abs"
        );
    }

    @Test
    public void testToStringCeil() {
        this.toStringAndCheck(
                NumberExpressionFunctionUnary.ceil(),
                "ceil"
        );
    }

    @Test
    public void testToStringFloor() {
        this.toStringAndCheck(
                NumberExpressionFunctionUnary.floor(),
                "floor"
        );
    }

    @Test
    public void testToStringRound() {
        this.toStringAndCheck(
                NumberExpressionFunctionUnary.round(),
                "round"
        );
    }

    @Override
    public NumberExpressionFunctionUnary<ExpressionFunctionContext> createBiFunction() {
        return NumberExpressionFunctionUnary.absolute();
    }

    @Override
    public Class<NumberExpressionFunctionUnary<ExpressionFunctionContext>> type() {
        return Cast.to(NumberExpressionFunctionUnary.class);
    }
}
