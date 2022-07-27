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
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionNumberKind;

public final class NumberExpressionFunctionRandomBetweenTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionRandomBetween<ExpressionEvaluationContext>> {

    @Test
    public void testZeroParameters() {
        final ExpressionEvaluationContext context = this.createContext();

        final ExpressionNumberKind kind = context.expressionNumberKind();
        final ExpressionNumber lower = kind.create(10);
        final ExpressionNumber upper = kind.create(20);

        final ExpressionNumber random = this.createBiFunction()
                .apply(
                        Lists.of(lower, upper),
                        context
                );

        this.checkEquals(
                random,
                random.round(context),
                () -> "random should not have decimals"
        );

        this.checkEquals(
                true,
                random.greaterThanEquals(lower),
                () -> "random (" + random + ") >= lower(" + lower + ")"
        );

        this.checkEquals(
                true,
                random.lessThan(upper),
                () -> "random (" + random + ") >= upper(" + upper + ")"
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createBiFunction(),
                "randomBetween"
        );
    }

    @Override
    public NumberExpressionFunctionRandomBetween<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionRandomBetween.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 2;
    }

    @Override
    public Class<NumberExpressionFunctionRandomBetween<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionRandomBetween.class);
    }
}
