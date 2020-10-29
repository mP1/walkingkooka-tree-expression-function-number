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
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.FakeExpressionFunctionContext;

import java.util.Collections;

public final class CountNumberExpressionFunctionTest extends NumberExpressionFunctionTestCase<CountNumberExpressionFunction, ExpressionNumber> {

    private final static ExpressionNumberKind KIND = ExpressionNumberKind.DEFAULT;

    @Test
    public void testZeroParameters() {
        this.applyAndCheck2(Lists.empty(), KIND.create(0));
    }

    @Test
    public void testOneParameters() {
        this.applyAndCheck2(Lists.of(1), KIND.create(1));
    }

    @Test
    public void testTenParameters() {
        this.applyAndCheck2(Collections.nCopies(10, null), KIND.create(10));
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(CountNumberExpressionFunction.INSTANCE, "count");
    }

    @Override
    public CountNumberExpressionFunction createBiFunction() {
        return CountNumberExpressionFunction.INSTANCE;
    }

    @Override
    public ExpressionFunctionContext createContext() {
        return new FakeExpressionFunctionContext() {
            @Override
            public ExpressionNumberKind expressionNumberKind() {
                return KIND;
            }
        };
    }

    @Override
    public Class<CountNumberExpressionFunction> type() {
        return CountNumberExpressionFunction.class;
    }
}
