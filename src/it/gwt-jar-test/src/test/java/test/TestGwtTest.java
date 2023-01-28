package test;

import com.google.gwt.junit.client.GWTTestCase;

import walkingkooka.Cast;
import walkingkooka.Either;
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionNumberContexts;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.FakeExpressionEvaluationContext;
import walkingkooka.tree.expression.function.number.NumberExpressionFunctions;

public class TestGwtTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "test.Test";
    }

    public void testAssertEquals() {
        assertEquals(
                1,
                1
        );
    }

    public void testAbs() {
        final ExpressionNumber value = ExpressionNumberKind.DEFAULT.create(-1.5);
        final Object result = NumberExpressionFunctions.abs()
                .apply(Lists.of(value),
                        new FakeExpressionEvaluationContext() {

                            public <T> Either<T, String> convert(final Object v,
                                                                 final Class<T> target) {
                                assertEquals(value, v);
                                assertEquals(ExpressionNumber.class, target);

                                return this.successfulConversion(
                                        value,
                                        target
                                );
                            }
                        });
        assertEquals(
                value.abs(ExpressionNumberContexts.fake()),
                result
        );
    }
}
