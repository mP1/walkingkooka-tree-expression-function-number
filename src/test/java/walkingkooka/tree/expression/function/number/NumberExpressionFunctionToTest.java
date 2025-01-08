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

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class NumberExpressionFunctionToTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionTo<ExpressionEvaluationContext>> {

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
    public void testExpressionNumber() {
        final ExpressionNumber value = KIND.create(10);

        this.applyAndCheck(
            Lists.of(
                value
            ),
            this.createContext(),
            value
        );
    }

    @Override
    public NumberExpressionFunctionTo<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionTo.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }

    @Override
    public Class<NumberExpressionFunctionTo<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionTo.class);
    }
}
