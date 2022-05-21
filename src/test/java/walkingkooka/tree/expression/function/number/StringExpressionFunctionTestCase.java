/*
 * Copyright 2022 Miroslav Pokorny (github.com/mP1)
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

import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.FakeExpressionEvaluationContext;
import walkingkooka.tree.expression.function.ExpressionFunction;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Locale;

public abstract class StringExpressionFunctionTestCase<F extends ExpressionFunction<T, ExpressionEvaluationContext>, T> extends ExpressionFunctionTestCase<F, T> {

    final static ExpressionNumberKind KIND = ExpressionNumberKind.DEFAULT;

    StringExpressionFunctionTestCase() {
        super();
    }

    @Override
    public final ExpressionEvaluationContext createContext() {
        return new FakeExpressionEvaluationContext() {
            @Override
            public ExpressionNumberKind expressionNumberKind() {
                return KIND;
            }

            public MathContext mathContext() {
                return new MathContext(0, RoundingMode.HALF_UP);
            }

            @Override
            public char decimalSeparator() {
                return 'd';
            }

            @Override
            public char groupingSeparator() {
                return 'g';
            }

            @Override
            public char negativeSign() {
                return 'n';
            }

            @Override
            public Locale locale() {
                return Locale.forLanguageTag("EN-AU");
            }
        };
    }

    @Override
    public final String typeNamePrefix() {
        return StringExpressionFunction.class.getSimpleName();
    }
}
