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
import walkingkooka.NeverError;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionNumberContext;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.ExpressionNumberSign;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.BiFunction;

final class NumberExpressionFunctionUnary<C extends ExpressionEvaluationContext> extends NumberExpressionFunction<C> {

    /**
     * ABSOLUTE getter.
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionUnary<C> absolute() {
        return Cast.to(ABSOLUTE);
    }

    /**
     * Singleton
     */
    private static final NumberExpressionFunctionUnary<?> ABSOLUTE = new NumberExpressionFunctionUnary<>(
        "abs",
        (n, c) -> n.abs(c)
    );

    /**
     * CEIL getter.
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionUnary<C> ceil() {
        return Cast.to(CEIL);
    }

    /**
     * CEIL Singleton
     */
    private static final NumberExpressionFunctionUnary<?> CEIL = new NumberExpressionFunctionUnary<>(
        "ceil",
        (n, c) -> n.ceil(c)
    );

    /**
     * EVEN getter.
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionUnary<C> even() {
        return Cast.to(EVEN);
    }

    private final static ExpressionNumber TWO = ExpressionNumberKind.DEFAULT.create(2);

    // https://github.com/cuba-platform/apache-poi/blob/master/poi/src/java/org/apache/poi/ss/formula/functions/Even.java
    /**
     * EVEN Singleton
     */
    private static final NumberExpressionFunctionUnary<?> EVEN = new NumberExpressionFunctionUnary<>(
        "even",
        (n, c) -> {
            final ExpressionNumberSign sign = n.sign();
            final ExpressionNumberKind kind = c.expressionNumberKind();

            final ExpressionNumber number;

            switch (sign) {
                case NEGATIVE:
                    number = calculateEven(
                        n.negate(c),
                        c
                    ).negate(c);
                    break;
                case ZERO:
                    number = kind.zero();
                    break;
                case POSITIVE:
                    number = calculateEven(n, c);
                    break;
                default:
                    NeverError.unhandledCase(sign, ExpressionNumberSign.values());
                    number = null;
                    break;
            }

            return number;
        }
    );

    private static ExpressionNumber calculateEven(final ExpressionNumber number,
                                                  final ExpressionNumberContext context) {
        final ExpressionNumberKind kind = context.expressionNumberKind();

        final ExpressionNumber clearBit0 = number.setKind(kind)
            .andNot(kind.one());

        return clearBit0.equals(number) ?
            clearBit0 :
            clearBit0.add(TWO, context);
    }

    /**
     * EXP getter.
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionUnary<C> exp() {
        return Cast.to(EXP);
    }

    /**
     * EXP Singleton
     */
    private static final NumberExpressionFunctionUnary<?> EXP = new NumberExpressionFunctionUnary<>(
        "exp",
        (n, c) -> n.exp(c)
    );

    /**
     * FLOOR getter.
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionUnary<C> floor() {
        return Cast.to(FLOOR);
    }

    /**
     * FLOOR Singleton
     */
    private static final NumberExpressionFunctionUnary<?> FLOOR = new NumberExpressionFunctionUnary<>(
        "floor",
        (n, c) -> n.floor(c)
    );

    /**
     * INT getter.
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionUnary<C> intFunction() {
        return Cast.to(INT);
    }

    /**
     * INT Singleton
     */
    private static final NumberExpressionFunctionUnary<?> INT = new NumberExpressionFunctionUnary<>(
        "int",
        (n, c) -> round(n, c, RoundingMode.DOWN)
    );

    /**
     * LN getter.
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionUnary<C> ln() {
        return Cast.to(LN);
    }

    /**
     * LN Singleton
     */
    private static final NumberExpressionFunctionUnary<?> LN = new NumberExpressionFunctionUnary<>(
        "ln",
        (n, c) -> n.ln(c)
    );

    /**
     * LOG10 getter.
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionUnary<C> log10() {
        return Cast.to(LOG10);
    }

    /**
     * LOG10 Singleton
     */
    private static final NumberExpressionFunctionUnary<?> LOG10 = new NumberExpressionFunctionUnary<>(
        "log10",
        (n, c) -> n.log10(c)
    );

    /**
     * ODD getter.
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionUnary<C> odd() {
        return Cast.to(ODD);
    }

    /**
     * ODD Singleton
     * <br>
     * https://github.com/cuba-platform/apache-poi/blob/master/poi/src/java/org/apache/poi/ss/formula/functions/Odd.java
     */
    private static final NumberExpressionFunctionUnary<?> ODD = new NumberExpressionFunctionUnary<>(
        "odd",
        (n, c) -> {
            final ExpressionNumberSign sign = n.sign();
            final ExpressionNumberKind kind = c.expressionNumberKind();

            final ExpressionNumber number;

            switch (sign) {
                case NEGATIVE:
                    number = calculateOdd(
                        n.negate(c),
                        c
                    ).negate(c);
                    break;
                case ZERO:
                    number = kind.create(1);
                    break;
                case POSITIVE:
                    number = calculateOdd(n, c);
                    break;
                default:
                    NeverError.unhandledCase(sign, ExpressionNumberSign.values());
                    number = null;
                    break;
            }

            return number;
        }
    );

    private static ExpressionNumber calculateOdd(final ExpressionNumber number,
                                                 final ExpressionNumberContext context) {
        final ExpressionNumberKind kind = context.expressionNumberKind();

        final ExpressionNumber one = kind.one();

        final ExpressionNumber plus1 = number.setKind(kind)
            .add(one, context);

        final ExpressionNumber clearBit0 = plus1.andNot(one);

        return plus1.equals(clearBit0) ?
            clearBit0.subtract(one, context) :
            clearBit0.add(one, context);
    }

    /**
     * ROUND getter.
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionUnary<C> round() {
        return Cast.to(ROUND);
    }

    /**
     * ROUND Singleton
     */
    private static final NumberExpressionFunctionUnary<?> ROUND = new NumberExpressionFunctionUnary<>(
        "round",
        (n, c) -> round(n, c, RoundingMode.HALF_UP)
    );

    private static ExpressionNumber round(final ExpressionNumber number,
                                          final ExpressionNumberContext context,
                                          final RoundingMode roundingMode) {
        return number.round(
            NumberExpressionFunctionUnaryExpressionNumberContext.with(
                new MathContext(
                    context.mathContext()
                        .getPrecision(),
                    roundingMode
                )
            )
        );
    }

    /**
     * SIGN getter.
     * <br>
     * https://exceljet.net/excel-functions/excel-sign-function
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionUnary<C> sign() {
        return Cast.to(SIGN);
    }

    /**
     * SIGN Singleton
     */
    private static final NumberExpressionFunctionUnary<?> SIGN = new NumberExpressionFunctionUnary<>(
        "sign",
        (n, c) -> c.expressionNumberKind()
            .setSign(
                n.sign()
            )
    );

    /**
     * SQRT getter.
     * <br>
     * https://exceljet.net/excel-functions/excel-sqrt-function
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionUnary<C> sqrt() {
        return Cast.to(SQRT);
    }

    /**
     * SQRT Singleton
     */
    private static final NumberExpressionFunctionUnary<?> SQRT = new NumberExpressionFunctionUnary<>(
        "sqrt",
        (n, c) -> n.sqrt(c)
    );

    /**
     * Private ctor
     */
    private NumberExpressionFunctionUnary(final String name,
                                          final BiFunction<ExpressionNumber, C, ExpressionNumber> function) {
        super(name);
        this.function = function;
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS_VALUE;
    }

    @Override
    public ExpressionNumber apply(final List<Object> parameters,
                                  final C context) {
        this.checkParameterCount(parameters);

        return this.function.apply(
            NUMBER.getOrFail(parameters, 0),
            context
        );
    }

    private final BiFunction<ExpressionNumber, C, ExpressionNumber> function;
}
