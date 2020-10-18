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

import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.select.NodeSelectorException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Base for any function that expects a single number.
 */
abstract class NumberExpressionFunction2 implements ExpressionFunction<Number> {

    /**
     * Package private ctor
     */
    NumberExpressionFunction2() {
        super();
    }

    @Override
    public Number apply(final List<Object> parameters,
                        final ExpressionFunctionContext context) {
        this.checkParameterCount(parameters, 1);

        return NumberExpressionFunction2ExpressionNumberVisitor.apply(this.number(parameters, 0, context),
                this,
                context);
    }

    abstract Number applyBigDecimal(final BigDecimal number);

    abstract Number applyBigInteger(final BigInteger number);

    abstract Number applyDouble(final Double number);

    abstract Number applyLong(final Long number);

    /**
     * Checks and complains if the parameter count doesnt match the expected count.
     */
    final void checkParameterCount(final List<Object> parameters,
                                   final int expectedCount) {
        final int count = parameters.size();
        if (expectedCount != count) {
            throw new IllegalArgumentException("Expected " + expectedCount + " but got " + count + "=" + parameters);
        }
    }

    /**
     * Converts a value into a {@link Number}.
     */
    final Number number(final Object value,
                        final ExpressionFunctionContext context) {
        return context.convertOrFail(value, Number.class);
    }

    /**
     * Type safe number parameter getter.
     */
    final Number number(final List<?> parameters,
                        final int i,
                        final ExpressionFunctionContext context) {
        return this.number(this.parameter(parameters, i), context);
    }

    /**
     * Retrieves the parameter at the index or throws a nice exception message.
     */
    final <TT> TT parameter(final List<?> parameters,
                            final int i,
                            final Class<TT> type,
                            final ExpressionFunctionContext context) {
        return context.convertOrFail(this.parameter(parameters, i), type);
    }

    /**
     * Retrieves the parameter at the index or throws a nice exception message.
     */
    final Object parameter(final List<?> parameters,
                           final int i) {
        final int count = parameters.size();
        if (i < 0 || i >= count) {
            throw new NodeSelectorException("Parameter " + i + " missing from " + parameters);
        }
        return parameters.get(i);
    }

    @Override
    public String toString() {
        return this.name().toString();
    }
}
