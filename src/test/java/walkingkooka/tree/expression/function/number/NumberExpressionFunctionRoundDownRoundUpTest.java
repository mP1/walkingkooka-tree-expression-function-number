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
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

public final class NumberExpressionFunctionRoundDownRoundUpTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionRoundDownRoundUp<ExpressionFunctionContext>> {

    // roundDown.......................................................................................................
    // https://exceljet.net/excel-functions/excel-rounddown-function

    @Test
    public void testRoundDownDigits0() {
        this.roundDownAndCheck(
                5.7899,
                0,
                5
        );
    }

    @Test
    public void testRoundDownDigits1() {
        this.roundDownAndCheck(
                5.7899,
                1,
                5.7
        );
    }

    @Test
    public void testRoundDownDigits2() {
        this.roundDownAndCheck(
                5.7899,
                2,
                5.78
        );
    }

    @Test
    public void testRoundDownDigits3() {
        this.roundDownAndCheck(
                5.7899,
                3,
                5.789
        );
    }

    @Test
    public void testRoundDownDigits4() {
        this.roundDownAndCheck(
                5.7899,
                4,
                5.7899
        );
    }

    @Test
    public void testRoundDownDigitsMinus1() {
        this.roundDownAndCheck(
                27842.5,
                -1,
                27840
        );
    }

    @Test
    public void testRoundDownDigitsMinus2() {
        this.roundDownAndCheck(
                27842.5,
                -2,
                27800
        );
    }

    @Test
    public void testRoundDownDigitsMinus3() {
        this.roundDownAndCheck(
                27842.5,
                -3,
                27000
        );
    }

    @Test
    public void testRoundDownDigitsMinus4() {
        this.roundDownAndCheck(
                27842.5,
                -4,
                20000
        );
    }

    private void roundDownAndCheck(final Number number,
                                   final Number digits,
                                   final Number expected) {
        this.roundAndCheck(
                NumberExpressionFunctionRoundDownRoundUp.roundDown(),
                number,
                digits,
                expected
        );
    }

    // roundUp.........................................................................................................
// https://exceljet.net/excel-functions/excel-roundup-function

    @Test
    public void testRoundUpDigits0() {
        this.roundUpAndCheck(
                5.1242,
                0,
                6
        );
    }

    @Test
    public void testRoundUpDigits1() {
        this.roundUpAndCheck(
                5.1242,
                1,
                5.2
        );
    }

    @Test
    public void testRoundUpDigits2() {
        this.roundUpAndCheck(
                5.1242,
                2,
                5.13
        );
    }

    @Test
    public void testRoundUpDigits3() {
        this.roundUpAndCheck(
                5.1242,
                3,
                5.125
        );
    }

    @Test
    public void testRoundUpDigits4() {
        this.roundUpAndCheck(
                5.1242,
                4,
                5.1242
        );
    }

    @Test
    public void testRoundUpDigitsMinus1() {
        this.roundUpAndCheck(
                23242.3,
                -1,
                23250
        );
    }

    @Test
    public void testRoundUpDigitsMinus2() {
        this.roundUpAndCheck(
                23242.3,
                -2,
                23300
        );
    }

    @Test
    public void testRoundUpDigitsMinus3() {
        this.roundUpAndCheck(
                23242.3,
                -3,
                24000
        );
    }

    @Test
    public void testRoundUpDigitsMinus4() {
        this.roundUpAndCheck(
                23242.3,
                -4,
                30000
        );
    }

    private void roundUpAndCheck(final Number number,
                                 final Number digits,
                                 final Number expected) {
        this.roundAndCheck(
                NumberExpressionFunctionRoundDownRoundUp.roundUp(),
                number,
                digits,
                expected
        );
    }

    private void roundAndCheck(final NumberExpressionFunctionRoundDownRoundUp<ExpressionFunctionContext> function,
                               final Number number,
                               final Number digits,
                               final Number expected) {
        this.applyAndCheck(
                function,
                Lists.of(
                        KIND.create(number),
                        KIND.create(digits)
                ),
                this.createContext(),
                KIND.create(expected)
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToStringRoundDown() {
        this.toStringAndCheck(
                NumberExpressionFunctionRoundDownRoundUp.roundDown(),
                "roundDown"
        );
    }

    @Test
    public void testToStringRoundUp() {
        this.toStringAndCheck(
                NumberExpressionFunctionRoundDownRoundUp.roundUp(),
                "roundUp"
        );
    }

    // ExpressionFunctionTesting........................................................................................

    @Override
    public NumberExpressionFunctionRoundDownRoundUp<ExpressionFunctionContext> createBiFunction() {
        return NumberExpressionFunctionRoundDownRoundUp.roundDown();
    }

    @Override
    public Class<NumberExpressionFunctionRoundDownRoundUp<ExpressionFunctionContext>> type() {
        return Cast.to(NumberExpressionFunctionRoundDownRoundUp.class);
    }
}
