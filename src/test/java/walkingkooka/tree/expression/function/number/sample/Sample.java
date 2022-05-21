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

package walkingkooka.tree.expression.function.number.sample;

import walkingkooka.Cast;
import walkingkooka.Either;
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionNumberContexts;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.FakeExpressionEvaluationContext;
import walkingkooka.tree.expression.function.number.NumberExpressionFunctions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Sample {
    public static void main(final String[] args) {
        final ExpressionNumber value = ExpressionNumberKind.DEFAULT.create(-1.5);
        final Object result = NumberExpressionFunctions.abs()
                .apply(Lists.of(value),
                        new FakeExpressionEvaluationContext() {

                            public <T> Either<T, String> convert(final Object v,
                                                                 final Class<T> target) {
                                assertEquals(value, v);
                                assertEquals(ExpressionNumber.class, target);
                                return Cast.to(Either.left(value));
                            }
                        });
        assertEquals(value.abs(ExpressionNumberContexts.fake()), result);
    }
}
