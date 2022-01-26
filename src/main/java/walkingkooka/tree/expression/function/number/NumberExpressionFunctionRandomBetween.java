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
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

// https://support.google.com/docs/answer/3093438?hl=en&ref_topic=3105474
final class NumberExpressionFunctionRandomBetween<C extends ExpressionFunctionContext> extends NumberExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> NumberExpressionFunctionRandomBetween<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final NumberExpressionFunctionRandomBetween<?> INSTANCE = new NumberExpressionFunctionRandomBetween<>();

    /**
     * Private ctor
     */
    private NumberExpressionFunctionRandomBetween() {
        super("randomBetween");
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final ExpressionFunctionParameter<ExpressionNumber> LOWER = ExpressionFunctionParameterName.with("lower")
            .required(ExpressionNumber.class);

    private final ExpressionFunctionParameter<ExpressionNumber> UPPER = ExpressionFunctionParameterName.with("upper")
            .required(ExpressionNumber.class);

    private final List<ExpressionFunctionParameter<?>> PARAMETERS = Lists.of(
            LOWER,
            UPPER
    );

    @Override
    public ExpressionNumber apply(final List<Object> parameters,
                                  final C context) {
        this.checkParameterCount(parameters);

        return context.expressionNumberKind()
                .randomBetween(
                        LOWER.getOrFail(parameters, 0),
                        UPPER.getOrFail(parameters, 1),
                        context
                );
    }
}
