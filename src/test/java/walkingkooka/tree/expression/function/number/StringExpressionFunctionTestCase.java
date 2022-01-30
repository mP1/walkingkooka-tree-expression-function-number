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

import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.FakeExpressionFunctionContext;

public abstract class StringExpressionFunctionTestCase<F extends ExpressionFunction<T, ExpressionFunctionContext>, T> extends ExpressionFunctionTestCase<F, T> {

    final static ExpressionNumberKind KIND = ExpressionNumberKind.DEFAULT;

    StringExpressionFunctionTestCase() {
        super();
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
    public final String typeNamePrefix() {
        return StringExpressionFunction.class.getSimpleName();
    }
}
