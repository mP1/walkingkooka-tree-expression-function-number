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
import walkingkooka.tree.expression.ExpressionEvaluationContext;

public final class NumberExpressionFunctionConstantsTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionConstants<ExpressionEvaluationContext>> {

    @Test
    public void testE() {
        this.applyAndCheck2(
                NumberExpressionFunctionConstants.e(),
                ExpressionEvaluationContext.NO_PARAMETERS,
                KIND.create(Math.E)
        );
    }

    @Test
    public void testPI() {
        this.applyAndCheck2(
                NumberExpressionFunctionConstants.pi(),
                ExpressionEvaluationContext.NO_PARAMETERS,
                KIND.create(Math.PI)
        );
    }

    @Test
    public void testToStringE() {
        this.toStringAndCheck(
                NumberExpressionFunctionConstants.e(),
                "e"
        );
    }

    @Test
    public void testToStringPi() {
        this.toStringAndCheck(
                NumberExpressionFunctionConstants.pi(),
                "pi"
        );
    }

    @Override
    public NumberExpressionFunctionConstants<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionConstants.e();
    }

    @Override
    public Class<NumberExpressionFunctionConstants<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionConstants.class);
    }
}
