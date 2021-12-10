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
import walkingkooka.Either;
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.tree.expression.ExpressionEvaluationContexts;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionTesting;
import walkingkooka.tree.expression.function.FakeExpressionFunctionContext;

import java.math.BigDecimal;
import java.util.List;

public abstract class NumberExpressionFunctionTestCase<F extends ExpressionFunction<T, ExpressionFunctionContext>, T> implements ExpressionFunctionTesting<F, T, ExpressionFunctionContext>,
        ClassTesting2<F> {

    final static ExpressionNumberKind KIND = ExpressionNumberKind.DEFAULT;

    NumberExpressionFunctionTestCase() {
        super();
    }

    @Test
    public final void testIsPureTrue() {
        this.isPureAndCheck(
                this.createBiFunction(),
                ExpressionEvaluationContexts.fake(),
                true
        );
    }

    final void apply2(final Object... parameters) {
        this.createBiFunction().apply(parameters(parameters), this.createContext());
    }

    final void applyAndCheck2(final List<Object> parameters,
                              final T result) {
        this.applyAndCheck2(this.createBiFunction(), parameters, result);
    }

    final void applyAndCheck2(final ExpressionFunction<T, ExpressionFunctionContext> function,
                              final List<Object> parameters,
                              final T result) {
        this.applyAndCheck2(function, parameters, this.createContext(), result);
    }

    @Override
    public ExpressionFunctionContext createContext() {
        return new FakeExpressionFunctionContext() {
            @Override
            public ExpressionNumberKind expressionNumberKind() {
                return KIND;
            }

            @Override
            public <TT> Either<TT, String> convert(final Object value,
                                                   final Class<TT> target) {
                try {
                    final Number number = value instanceof String ?
                            new BigDecimal((String) value) :
                            (Number) value;
                    return Either.left(target.cast(KIND.create(number)));
                } catch (final Exception fail) {
                    return this.failConversion(value, target);
                }
            }
        };
    }

    @Override
    public final JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}
