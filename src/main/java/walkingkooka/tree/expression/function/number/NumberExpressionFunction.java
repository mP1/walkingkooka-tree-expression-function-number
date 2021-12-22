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

import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionPurityContext;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;
import walkingkooka.tree.select.NodeSelectorException;

import java.util.List;

/**
 * Base for any function that handles and requires numbers.
 */
abstract class NumberExpressionFunction<T, C extends ExpressionFunctionContext> implements ExpressionFunction<T, C> {

    /**
     * Package private ctor
     */
    NumberExpressionFunction() {
        super();
    }

    @Override
    public final List<ExpressionFunctionParameter<?>> parameters() {
        return this.lsLastParameterVariable() ?
                PARAMETERS_VALUES :
                PARAMETERS_VALUE;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS_VALUE = Lists.of(
            ExpressionFunctionParameterName.with("value")
                    .setType(Boolean.class)
    );

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS_VALUES = Lists.of(
            ExpressionFunctionParameterName.with("values")
                    .setType(Boolean.class)
    );

    @Override
    public final boolean lsLastParameterVariable() {
        return !(this instanceof UnaryNumberExpressionFunction);
    }

    /**
     * All number functions are pure. Does not assume anyting about any parameters.
     */
    @Override
    public final boolean isPure(final ExpressionPurityContext context) {
        return true;
    }

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
    final ExpressionNumber expressionNumber(final Object value,
                                            final ExpressionFunctionContext context) {
        return context.convertOrFail(value, ExpressionNumber.class);
    }

    /**
     * Type safe expressionNumber parameter getter.
     */
    final ExpressionNumber expressionNumber(final List<?> parameters,
                                            final int i,
                                            final ExpressionFunctionContext context) {
        return this.expressionNumber(this.parameter(parameters, i), context);
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
    public final boolean resolveReferences() {
        return true;
    }

    @Override
    public final String toString() {
        return this.name().toString();
    }
}
