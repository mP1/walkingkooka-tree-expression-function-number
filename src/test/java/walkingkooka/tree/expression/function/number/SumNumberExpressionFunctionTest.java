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
import walkingkooka.convert.ConversionException;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class SumNumberExpressionFunctionTest extends NumberExpressionFunctionTestCase<SumNumberExpressionFunction<ExpressionFunctionContext>, ExpressionNumber> {

    private final static ExpressionNumberKind KIND = ExpressionNumberKind.DEFAULT;

    @Test
    public void testNonNumericConvertableFails() {
        assertThrows(ConversionException.class, () -> this.createBiFunction().apply(Lists.of("hello"), this.createContext()));
    }

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
        this.applyAndCheck2(Collections.nCopies(10, 20), KIND.create(10 * 20));
    }

    @Test
    public void testDifferentNumberTypes() {
        this.applyAndCheck2(List.of((byte) 1, (short) 2, 3, 4L, BigInteger.valueOf(5), BigDecimal.valueOf(6), KIND.create(7), 8.5), KIND.create(1 + 2 + 3 + 4 + 5 + 6 + 7 + 8.5));
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "sum");
    }

    @Override
    public SumNumberExpressionFunction<ExpressionFunctionContext> createBiFunction() {
        return SumNumberExpressionFunction.instance();
    }

    @Override
    public Class<SumNumberExpressionFunction<ExpressionFunctionContext>> type() {
        return Cast.to(SumNumberExpressionFunction.class);
    }
}
