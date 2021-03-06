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
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class UnaryNumberExpressionFunctionTestCase<F extends UnaryNumberExpressionFunction<ExpressionFunctionContext>> extends NumberExpressionFunctionTestCase<F, ExpressionNumber> {

    UnaryNumberExpressionFunctionTestCase() {
        super();
    }

    @Test
    public final void testZeroArgumentsFails() {
        this.applyAndFail();
    }

    @Test
    public final void testTwoArgumentsFails() {
        this.applyAndFail(1, 2);
    }

    private void applyAndFail(final Object... parameters) {
        assertThrows(IllegalArgumentException.class, () -> this.apply2(parameters));
    }

    @Test
    public final void testToString() {
        this.toStringAndCheck(this.createBiFunction(), this.functionToString());
    }

    abstract String functionToString();

    final void applyAndCheck3(final Number number) {
        this.applyAndCheck3(number, number);
    }

    final void applyAndCheck3(final Object number, final Number expected) {
        this.applyAndCheck3(Lists.of(number), KIND.create(expected));
    }

    final void applyAndCheck3(final List<?> parameters, final ExpressionNumber expected) {
        this.applyAndCheck2(Cast.to(parameters), expected);
    }

    @Override
    public final String typeNamePrefix() {
        return "";
    }

    @Override
    public final String typeNameSuffix() {
        return Number.class.getSimpleName() + ExpressionFunction.class.getSimpleName();
    }

}
