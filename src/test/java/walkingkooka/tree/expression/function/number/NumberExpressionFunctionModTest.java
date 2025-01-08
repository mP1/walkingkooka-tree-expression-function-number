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
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumberKind;

public final class NumberExpressionFunctionModTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionMod<ExpressionEvaluationContext>> {

    private final static ExpressionNumberKind KIND = ExpressionNumberKind.BIG_DECIMAL; // necessary to avoid double precision loss

    @Test
    public void test3And5() {
        this.modAndCheck(
            3,
            5,
            3
        );
    }

    @Test
    public void test25And5() {
        this.modAndCheck(
            25,
            5,
            0
        );
    }

    @Test
    public void test10And3() {
        this.modAndCheck(
            10,
            3,
            1
        );
    }

    @Test
    public void test61And10() {
        this.modAndCheck(
            61,
            10,
            1
        );
    }

// <li>mod(3.4, 2) = 1.4</li>
// <li>mod(-3.4, 2) = 0.6</li>
// <li>mod(-3.4, -2) = -1.4</li>
// <li>mod(3.4, -2) = -0.6</li>


    @Test
    public void test3Point4And2() {
        this.modAndCheck(
            3.4,
            2,
            1.4
        );
    }

    @Test
    public void testMinus3Point4And2() {
        this.modAndCheck(
            -3.4,
            2,
            0.6
        );
    }

    @Test
    public void testMinus3Point4AndMinus2() {
        this.modAndCheck(
            -3.4,
            -2,
            -1.4
        );
    }

    @Test
    public void test3Point4AndMinus2() {
        this.modAndCheck(
            3.4,
            -2,
            -0.6
        );
    }

    private void modAndCheck(final double num,
                             final double denom,
                             final double expected) {
        this.applyAndCheck(
            Lists.of(
                KIND.create(num),
                KIND.create(denom)
            ),
            this.createContext(KIND),
            KIND.create(expected)
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createBiFunction(),
            "mod"
        );
    }

    @Override
    public NumberExpressionFunctionMod<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionMod.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 2;
    }

    @Override
    public Class<NumberExpressionFunctionMod<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionMod.class);
    }
}
