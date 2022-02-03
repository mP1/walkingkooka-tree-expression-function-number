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
import walkingkooka.reflect.PublicStaticHelper;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import java.util.function.Consumer;

/**
 * Collection of static factory methods for numerous {@link ExpressionFunction}.
 */
public final class NumberExpressionFunctions implements PublicStaticHelper {

    /**
     * Visit all {@link ExpressionFunction functions}.
     */
    public static void visit(final Consumer<ExpressionFunction<?, ?>> consumer) {
        Lists.of(abs(),
                average(),
                ceil(),
                count(),
                e(),
                even(),
                fixed(),
                floor(),
                intFunction(),
                isEven(),
                isNumber(),
                isOdd(),
                ln(),
                log(),
                log10(),
                max(),
                min(),
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
                roundUp(),
                sign(),
                sqrt(),
                sum(),
                trunc()
        ).forEach(consumer);
    }

    /**
     * {@see NumberExpressionFunctionUnary#absolute}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> abs() {
        return NumberExpressionFunctionUnary.absolute();
    }

    /**
     * {@see NumberExpressionFunctionAverage}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> average() {
        return NumberExpressionFunctionAverage.instance();
    }

    /**
     * {@see NumberExpressionFunctionUnary#ceil}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> ceil() {
        return NumberExpressionFunctionUnary.ceil();
    }

    /**
     * {@see NumberExpressionFunctionCount}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> count() {
        return NumberExpressionFunctionCount.instance();
    }

    /**
     * {@see NumberExpressionFunctionConstants#e()}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> e() {
        return NumberExpressionFunctionConstants.e();
    }

    /**
     * {@see NumberExpressionFunctionUnary#even}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> even() {
        return NumberExpressionFunctionUnary.even();
    }

    /**
     * {@see StringExpressionFunctionFixed#fixed}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> fixed() {
        return StringExpressionFunctionFixed.instance();
    }

    /**
     * {@see NumberExpressionFunctionUnary#floor}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> floor() {
        return NumberExpressionFunctionUnary.floor();
    }

    /**
     * {@see NumberExpressionFunctionUnary#intFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> intFunction() {
        return NumberExpressionFunctionUnary.intFunction();
    }

    /**
     * {@see BooleanExpressionFunctionIsEvenIsOdd#isEven}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> isEven() {
        return BooleanExpressionFunctionIsEvenIsOdd.isEven();
    }

    /**
     * {@see BooleanExpressionFunctionIsNumber}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> isNumber() {
        return BooleanExpressionFunctionIsNumber.instance();
    }

    /**
     * {@see BooleanExpressionFunctionIsEvenIsOdd#isOdd}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> isOdd() {
        return BooleanExpressionFunctionIsEvenIsOdd.isOdd();
    }

    /**
     * {@see NumberExpressionFunctionUnary#ln}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> ln() {
        return NumberExpressionFunctionUnary.ln();
    }

    /**
     * {@see NumberExpressionFunctionLog}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> log() {
        return NumberExpressionFunctionLog.instance();
    }

    /**
     * {@see NumberExpressionFunctionUnary#log10}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> log10() {
        return NumberExpressionFunctionUnary.log10();
    }

    /**
     * {@see NumberExpressionFunctionMax}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> max() {
        return NumberExpressionFunctionMax.instance();
    }

    /**
     * {@see NumberExpressionFunctionMin}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> min() {
        return NumberExpressionFunctionMin.instance();
    }

    /**
     * {@see NumberExpressionFunctionMod}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> mod() {
        return NumberExpressionFunctionMod.instance();
    }

    /**
     * {@see NumberExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> number() {
        return NumberExpressionFunctionTo.instance();
    }

    /**
     * {@see NumberExpressionFunctionUnary#odd}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> odd() {
        return NumberExpressionFunctionUnary.odd();
    }

    /**
     * {@see NumberExpressionFunctionConstants#pi()}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> pi() {
        return NumberExpressionFunctionConstants.pi();
    }

    /**
     * {@see NumberExpressionFunctionProduct}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> product() {
        return NumberExpressionFunctionProduct.instance();
    }

    /**
     * {@see NumberExpressionFunctionQuotient}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> quotient() {
        return NumberExpressionFunctionQuotient.instance();
    }

    /**
     * {@see NumberExpressionFunctionRandom}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> random() {
        return NumberExpressionFunctionRandom.instance();
    }

    /**
     * {@see NumberExpressionFunctionRandomBetween}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> randomBetween() {
        return NumberExpressionFunctionRandomBetween.instance();
    }

    /**
     * {@see StringExpressionFunctionRoman}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> roman() {
        return StringExpressionFunctionRoman.instance();
    }

    /**
     * {@see NumberExpressionFunctionUnary#round}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> round() {
        return NumberExpressionFunctionUnary.round();
    }

    /**
     * {@see NumberExpressionFunctionRoundDownRoundUp#roundDown}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> roundDown() {
        return NumberExpressionFunctionRoundDownRoundUp.roundDown();
    }

    /**
     * {@see NumberExpressionFunctionRoundDownRoundUp#roundUp}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> roundUp() {
        return NumberExpressionFunctionRoundDownRoundUp.roundUp();
    }

    /**
     * {@see NumberExpressionFunctionUnary#sign}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> sign() {
        return NumberExpressionFunctionUnary.sign();
    }

    /**
     * {@see NumberExpressionFunctionUnary#sqrt}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> sqrt() {
        return NumberExpressionFunctionUnary.sqrt();
    }

    /**
     * {@see NumberExpressionFunctionSum}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> sum() {
        return NumberExpressionFunctionSum.instance();
    }

    /**
     * {@see NumberExpressionFunctionTrunc}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> trunc() {
        return NumberExpressionFunctionTrunc.instance();
    }

    /**
     * Stops creation
     */
    private NumberExpressionFunctions() {
        throw new UnsupportedOperationException();
    }
}
