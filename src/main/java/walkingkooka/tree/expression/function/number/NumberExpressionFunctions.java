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

import walkingkooka.collect.set.Sets;
import walkingkooka.net.Url;
import walkingkooka.reflect.PublicStaticHelper;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.provider.ExpressionFunctionProvider;
import walkingkooka.tree.expression.function.provider.ExpressionFunctionProviders;

/**
 * Collection of static factory methods for numerous {@link ExpressionFunction}.
 */
public final class NumberExpressionFunctions implements PublicStaticHelper {

    /**
     * An {@link ExpressionFunctionProvider} with all the functions in this project.
     */
    public static ExpressionFunctionProvider expressionFunctionProvider() {
        return ExpressionFunctionProviders.basic(
                Url.parseAbsolute("https://github.com/mP1/walkingkooka-tree-expression-function-number/"),
                Sets.of(
                        abs(),
                        base(),
                        ceil(),
                        decimal(),
                        delta(),
                        e(),
                        even(),
                        exp(),
                        fixed(),
                        floor(),
                        intFunction(),
                        isEven(),
                        isNumber(),
                        isOdd(),
                        ln(),
                        log(),
                        log10(),
                        mod(),
                        number(),
                        odd(),
                        pi(),
                        product(),
                        quotient(),
                        random(),
                        randomBetween(),
                        roman(),
                        round(),
                        roundDown(),
                        roundHalf(),
                        roundUp(),
                        sign(),
                        sqrt(),
                        trunc()
                )
        );
    }

    /**
     * {@see NumberExpressionFunctionUnary#absolute}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> abs() {
        return NumberExpressionFunctionUnary.absolute();
    }

    /**
     * {@see StringExpressionFunctionBase}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> base() {
        return StringExpressionFunctionBase.instance();
    }

    /**
     * {@see NumberExpressionFunctionUnary#ceil}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> ceil() {
        return NumberExpressionFunctionUnary.ceil();
    }

    /**
     * {@see NumberExpressionFunctionDecimal}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> decimal() {
        return NumberExpressionFunctionDecimal.instance();
    }

    /**
     * {@see NumberExpressionFunctionDelta}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> delta() {
        return NumberExpressionFunctionDelta.instance();
    }

    /**
     * {@see NumberExpressionFunctionConstants#e()}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> e() {
        return NumberExpressionFunctionConstants.e();
    }

    /**
     * {@see NumberExpressionFunctionUnary#even}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> even() {
        return NumberExpressionFunctionUnary.even();
    }

    /**
     * {@see NumberExpressionFunctionUnary#exp}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> exp() {
        return NumberExpressionFunctionUnary.exp();
    }

    /**
     * {@see StringExpressionFunctionFixed#fixed}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> fixed() {
        return StringExpressionFunctionFixed.instance();
    }

    /**
     * {@see NumberExpressionFunctionUnary#floor}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> floor() {
        return NumberExpressionFunctionUnary.floor();
    }

    /**
     * {@see NumberExpressionFunctionUnary#intFunction}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> intFunction() {
        return NumberExpressionFunctionUnary.intFunction();
    }

    /**
     * {@see BooleanExpressionFunctionIsEvenIsOdd#isEven}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> isEven() {
        return BooleanExpressionFunctionIsEvenIsOdd.isEven();
    }

    /**
     * {@see BooleanExpressionFunctionIsNumber}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> isNumber() {
        return BooleanExpressionFunctionIsNumber.instance();
    }

    /**
     * {@see BooleanExpressionFunctionIsEvenIsOdd#isOdd}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> isOdd() {
        return BooleanExpressionFunctionIsEvenIsOdd.isOdd();
    }

    /**
     * {@see NumberExpressionFunctionUnary#ln}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> ln() {
        return NumberExpressionFunctionUnary.ln();
    }

    /**
     * {@see NumberExpressionFunctionLog}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> log() {
        return NumberExpressionFunctionLog.instance();
    }

    /**
     * {@see NumberExpressionFunctionUnary#log10}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> log10() {
        return NumberExpressionFunctionUnary.log10();
    }

    /**
     * {@see NumberExpressionFunctionMod}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> mod() {
        return NumberExpressionFunctionMod.instance();
    }

    /**
     * {@see NumberExpressionFunction}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> number() {
        return NumberExpressionFunctionTo.instance();
    }

    /**
     * {@see NumberExpressionFunctionUnary#odd}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> odd() {
        return NumberExpressionFunctionUnary.odd();
    }

    /**
     * {@see NumberExpressionFunctionConstants#pi()}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> pi() {
        return NumberExpressionFunctionConstants.pi();
    }

    /**
     * {@see NumberExpressionFunctionProduct}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> product() {
        return NumberExpressionFunctionProduct.instance();
    }

    /**
     * {@see NumberExpressionFunctionQuotient}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> quotient() {
        return NumberExpressionFunctionQuotient.instance();
    }

    /**
     * {@see NumberExpressionFunctionRandom}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> random() {
        return NumberExpressionFunctionRandom.instance();
    }

    /**
     * {@see NumberExpressionFunctionRandomBetween}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> randomBetween() {
        return NumberExpressionFunctionRandomBetween.instance();
    }

    /**
     * {@see StringExpressionFunctionRoman}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> roman() {
        return StringExpressionFunctionRoman.instance();
    }

    /**
     * {@see NumberExpressionFunctionUnary#round}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> round() {
        return NumberExpressionFunctionUnary.round();
    }

    /**
     * {@see NumberExpressionFunctionRoundDownHalfUp#roundDown}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> roundDown() {
        return NumberExpressionFunctionRoundDownHalfUp.roundDown();
    }

    /**
     * {@see NumberExpressionFunctionRoundHalfRoundUp#roundHalf}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> roundHalf() {
        return NumberExpressionFunctionRoundDownHalfUp.roundHalf();
    }

    /**
     * {@see NumberExpressionFunctionRoundDownHalfUp#roundUp}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> roundUp() {
        return NumberExpressionFunctionRoundDownHalfUp.roundUp();
    }

    /**
     * {@see NumberExpressionFunctionUnary#sign}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> sign() {
        return NumberExpressionFunctionUnary.sign();
    }

    /**
     * {@see NumberExpressionFunctionUnary#sqrt}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> sqrt() {
        return NumberExpressionFunctionUnary.sqrt();
    }

    /**
     * {@see NumberExpressionFunctionTrunc}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> trunc() {
        return NumberExpressionFunctionTrunc.instance();
    }

    /**
     * Stops creation
     */
    private NumberExpressionFunctions() {
        throw new UnsupportedOperationException();
    }
}
