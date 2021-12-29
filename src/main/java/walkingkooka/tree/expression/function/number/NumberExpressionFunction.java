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

import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionPurityContext;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * Base for any function that handles and requires numbers.
 */
abstract class NumberExpressionFunction<C extends ExpressionFunctionContext> implements ExpressionFunction<ExpressionNumber, C> {

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

    final static ExpressionFunctionParameter<ExpressionNumber> VALUE = ExpressionFunctionParameterName.with("value")
            .setType(ExpressionNumber.class);

    final static ExpressionFunctionParameter<List> VALUES = ExpressionFunctionParameterName.with("values")
            .setType(List.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS_VALUE = ExpressionFunctionParameter.list(VALUE);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS_VALUES = ExpressionFunctionParameter.list(VALUES);

    @Override
    public final boolean lsLastParameterVariable() {
        return !(this instanceof NumberExpressionFunctionUnary || this instanceof NumberExpressionFunctionTo);
    }

    @Override
    public final Class<ExpressionNumber> returnType() {
        return ExpressionNumber.class;
    }

    /**
     * All number functions are pure. Does not assume anything about any parameters.
     */
    @Override
    public final boolean isPure(final ExpressionPurityContext context) {
        return true;
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
