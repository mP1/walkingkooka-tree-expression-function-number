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
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.FakeExpressionEvaluationContext;

import java.math.MathContext;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class NumberExpressionFunctionUnaryTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionUnary<ExpressionEvaluationContext>> {

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

    // exp............................................................................................................

    @Test
    public void testExp0() {
        this.expAndCheck(0, 1);
    }

    @Test
    public void testExp10() {
        this.expAndCheck(10, 22026.465794806718);
    }

    private void expAndCheck(final double value,
                             final double expected) {
        this.applyAndCheck3(
                NumberExpressionFunctionUnary.exp(),
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

    // int............................................................................................................

    @Test
    public void testIntBigDecimal() {
        this.intBigDecimalAndCheck(1.25, 1L);
    }

    @Test
    public void testIntBigDecimal2() {
        this.intBigDecimalAndCheck(1.5, 1L);
    }

    @Test
    public void testIntBigDecimalInteger() {
        this.intBigDecimalAndCheck(-2L);
    }

    @Test
    public void testIntBigDecimalIntegerNegative() {
        this.intBigDecimalAndCheck(-34.2, -34);
    }

    private void intBigDecimalAndCheck(final Number value) {
        this.intBigDecimalAndCheck(value, value);
    }

    private void intBigDecimalAndCheck(final Number value,
                                       final Number expected) {
        this.applyAndCheck(
                NumberExpressionFunctionUnary.intFunction(),
                Lists.of(
                        ExpressionNumberKind.BIG_DECIMAL.create(value)
                ),
                new FakeExpressionEvaluationContext() {
                    @Override
                    public MathContext mathContext() {
                        return new MathContext(0, RoundingMode.DOWN);
                    }
                },
                ExpressionNumberKind.BIG_DECIMAL.create(expected)
        );
    }

    @Test
    public void testIntDoubleDown() {
        this.intDoubleAndCheck(1.25, 1L);
    }

    @Test
    public void testIntDoubleUp() {
        this.intDoubleAndCheck(1.5, 1L);
    }

    @Test
    public void testIntDoubleInteger() {
        this.intDoubleAndCheck(-2L);
    }

    @Test
    public void testIntDoubleIntegerNegative() {
        this.intDoubleAndCheck(-34.2, -34);
    }

    private void intDoubleAndCheck(final Number value) {
        this.intDoubleAndCheck(value, value);
    }

    private void intDoubleAndCheck(final Number value,
                                   final Number expected) {
        this.applyAndCheck3(
                NumberExpressionFunctionUnary.intFunction(),
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

    // log10............................................................................................................

    @Test
    public void testLog10NegativeFails() {
        assertThrows(
                IllegalArgumentException.class,
                () -> KIND.create(-1)
                        .log10(this.createContext())
        );
    }

    @Test
    public void testLog10_1() {
        this.log10AndCheck(1, 0);
    }

    @Test
    public void testLog10_10() {
        this.log10AndCheck(10, 1);
    }

    @Test
    public void testLog10_1000() {
        this.log10AndCheck(1000, 3);
    }

    private void log10AndCheck(final double value,
                               final double expected) {
        this.applyAndCheck3(
                NumberExpressionFunctionUnary.log10(),
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
    public void testRoundBigDecimalDown() {
        this.roundBigDecimalAndCheck(1.25, 1L);
    }

    @Test
    public void testRoundBigDecimalUp() {
        this.roundBigDecimalAndCheck(1.5, 2L);
    }

    @Test
    public void testRoundBigDecimalInteger() {
        this.roundBigDecimalAndCheck(-2L);
    }

    @Test
    public void testRoundBigDecimalIntegerNegative() {
        this.roundBigDecimalAndCheck(-34.2, -34);
    }

    private void roundBigDecimalAndCheck(final Number value) {
        this.roundBigDecimalAndCheck(value, value);
    }

    private void roundBigDecimalAndCheck(final Number value,
                                         final Number expected) {
        this.applyAndCheck(
                NumberExpressionFunctionUnary.round(),
                Lists.of(
                        ExpressionNumberKind.BIG_DECIMAL.create(value)
                ),
                new FakeExpressionEvaluationContext() {
                    @Override
                    public MathContext mathContext() {
                        return new MathContext(0, RoundingMode.HALF_UP);
                    }
                },
                ExpressionNumberKind.BIG_DECIMAL.create(expected)
        );
    }

    @Test
    public void testRoundDoubleDown() {
        this.roundDoubleAndCheck(1.25, 1L);
    }

    @Test
    public void testRoundDoubleUp() {
        this.roundDoubleAndCheck(1.5, 2L);
    }

    @Test
    public void testRoundDoubleInteger() {
        this.roundDoubleAndCheck(-2L);
    }

    @Test
    public void testRoundDoubleIntegerNegative() {
        this.roundDoubleAndCheck(-34.2, -34);
    }

    private void roundDoubleAndCheck(final Number value) {
        this.roundDoubleAndCheck(value, value);
    }

    private void roundDoubleAndCheck(final Number value,
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

    private void applyAndCheck3(final NumberExpressionFunctionUnary<ExpressionEvaluationContext> function,
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
    public NumberExpressionFunctionUnary<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionUnary.absolute();
    }

    @Override
    public Class<NumberExpressionFunctionUnary<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionUnary.class);
    }
}
