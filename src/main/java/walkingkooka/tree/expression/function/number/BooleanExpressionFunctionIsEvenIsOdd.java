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

package walkingkooka.tree.expression.function.number;

import walkingkooka.Cast;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.util.List;

// https://exceljet.net/excel-functions/excel-iseven-function
// https://exceljet.net/excel-functions/excel-isodd-function
final class BooleanExpressionFunctionIsEvenIsOdd<C extends ExpressionFunctionContext> extends BooleanExpressionFunction<C> {

    /**
     * isEven
     */
    static <C extends ExpressionFunctionContext> BooleanExpressionFunctionIsEvenIsOdd<C> isEven() {
        return Cast.to(IS_EVEN);
    }

    /**
     * isEven Singleton
     */
    private static final BooleanExpressionFunctionIsEvenIsOdd<?> IS_EVEN = new BooleanExpressionFunctionIsEvenIsOdd<>("isEven", false);

    /**
     * isOdd
     */
    static <C extends ExpressionFunctionContext> BooleanExpressionFunctionIsEvenIsOdd<C> isOdd() {
        return Cast.to(IS_ODD);
    }

    /**
     * isOdd Singleton
     */
    private static final BooleanExpressionFunctionIsEvenIsOdd<?> IS_ODD = new BooleanExpressionFunctionIsEvenIsOdd<>("isOdd", true);

    private BooleanExpressionFunctionIsEvenIsOdd(final String name, final boolean bit0) {
        super(name);
        this.bit0 = bit0;
    }

    @Override
    public Boolean apply(final List<Object> parameters,
                         final C context) {
        this.checkParameterCount(parameters);

        final ExpressionNumber number = PARAMETER.getOrFail(parameters, 0);
        if (null == number) {
            throw new NullPointerException("Parameter " + PARAMETER + " must not be null");
        }
        return this.bit0 == number.floor(context)
                .bigInteger()
                .testBit(0);
    }

    private final boolean bit0;

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private static final ExpressionFunctionParameter<ExpressionNumber> PARAMETER = ExpressionFunctionParameter.NUMBER;

    private static final List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(PARAMETER);
}
