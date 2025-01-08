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

public final class NumberExpressionFunctionDecimalTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionDecimal<ExpressionEvaluationContext>> {

    @Test
    public void testBase2() {
        this.decimalAndCheck(
            "1010",
            2,
            10
        );
    }

    @Test
    public void testBase36() {
        this.decimalAndCheck(
            "123Z",
            36,
            49391
        );
    }

    private void decimalAndCheck(final String text,
                                 final Number base,
                                 final Number expected) {
        this.applyAndCheck(
            Lists.of(
                text,
                KIND.create(base)
            ),
            this.createContext(),
            KIND.create(expected)
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createBiFunction(),
            "decimal"
        );
    }

    @Override
    public NumberExpressionFunctionDecimal<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionDecimal.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 2;
    }

    @Override
    public Class<NumberExpressionFunctionDecimal<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionDecimal.class);
    }
}
