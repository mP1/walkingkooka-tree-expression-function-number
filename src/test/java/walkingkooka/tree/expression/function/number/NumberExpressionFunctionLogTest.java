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

public final class NumberExpressionFunctionLogTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionLog<ExpressionEvaluationContext>> {

    @Test
    public void testLog16Base2() {
        this.applyAndCheck2(
                Lists.of(
                        KIND.create(16),
                        KIND.create(2)
                ),
                KIND.create(4)
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createBiFunction(),
                "log"
        );
    }

    @Override
    public NumberExpressionFunctionLog<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionLog.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 2;
    }

    @Override
    public Class<NumberExpressionFunctionLog<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionLog.class);
    }
}
