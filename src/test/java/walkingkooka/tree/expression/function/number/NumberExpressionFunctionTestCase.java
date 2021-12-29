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
import walkingkooka.collect.list.Lists;
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.tree.expression.ExpressionEvaluationContexts;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionTesting;
import walkingkooka.tree.expression.function.FakeExpressionFunctionContext;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class NumberExpressionFunctionTestCase<F extends ExpressionFunction<ExpressionNumber, ExpressionFunctionContext>>
        implements ExpressionFunctionTesting<F, ExpressionNumber, ExpressionFunctionContext>,
        ClassTesting2<F> {

    final static ExpressionNumberKind KIND = ExpressionNumberKind.DEFAULT;

    NumberExpressionFunctionTestCase() {
        super();
    }

    @Test
    public final void testDoesntConvert() {
        if (!(this instanceof CountNumberExpressionFunctionTest)) {
            assertThrows(
                    ClassCastException.class,
                    () -> {
                        this.createBiFunction()
                                .apply(Lists.of(1), this.createContext());
                    }
            );
        }
    }

    @Test
    public final void testIsPureTrue() {
        this.isPureAndCheck(
                this.createBiFunction(),
                ExpressionEvaluationContexts.fake(),
                true
        );
    }

    @Test
    public final void testResolveReferencesTrue() {
        this.resolveReferenceAndCheck(true);
    }

    final void apply2(final Number... parameters) {
        this.createBiFunction()
                .apply(parameters(parameters),
                        this.createContext()
                );
    }

    final void applyAndCheck2(final List<Object> parameters,
                              final ExpressionNumber result) {
        this.applyAndCheck2(
                this.createBiFunction(),
                parameters,
                result
        );
    }

    final void applyAndCheck2(final ExpressionFunction<ExpressionNumber, ExpressionFunctionContext> function,
                              final List<Object> parameters,
                              final ExpressionNumber result) {
        this.applyAndCheck2(
                function,
                parameters.stream()
                        .map(i -> KIND.create((Number) i))
                        .collect(Collectors.toList()),
                this.createContext(),
                result
        );
    }

    @Override
    public final ExpressionFunctionContext createContext() {
        return new FakeExpressionFunctionContext() {
            @Override
            public ExpressionNumberKind expressionNumberKind() {
                return KIND;
            }
        };
    }

    @Override
    public final JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}
