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

import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionFunctionName;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionPurityContext;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;
import java.util.Optional;

/**
 * Base for any function that handles and requires numbers.
 */
abstract class NumberExpressionFunction<C extends ExpressionEvaluationContext> implements ExpressionFunction<ExpressionNumber, C> {

    /**
     * Package private ctor
     */
    NumberExpressionFunction(final String name) {
        super();
        this.name = Optional.of(
                ExpressionFunctionName.with(name)
        );
    }

    @Override
    public final Optional<ExpressionFunctionName> name() {
        return this.name;
    }

    private final Optional<ExpressionFunctionName> name;

    final static ExpressionFunctionParameter<ExpressionNumber> NUMBER = ExpressionFunctionParameter.NUMBER
            .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    final static ExpressionFunctionParameter<List> NUMBERS = ExpressionFunctionParameterName.with("numbers")
            .required(List.class)
            .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_FLATTEN_RESOLVE_REFERENCES);

    final static List<ExpressionFunctionParameter<?>> PARAMETERS_VALUE = ExpressionFunctionParameter.list(NUMBER);

    @Override
    public final Class<ExpressionNumber> returnType() {
        return ExpressionNumber.class;
    }

    /**
     * All number functions are pure except for the randomXXX functions
     */
    @Override
    public final boolean isPure(final ExpressionPurityContext context) {
        return !(
                this instanceof NumberExpressionFunctionRandom ||
                        this instanceof NumberExpressionFunctionRandomBetween
        );
    }

    @Override
    public final String toString() {
        return this.name()
                .get()
                .toString();
    }
}
