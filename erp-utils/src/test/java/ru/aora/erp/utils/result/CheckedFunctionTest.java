package ru.aora.erp.utils.result;

import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import ru.aora.erp.utils.CheckedFunction;

import java.util.function.Function;

import static junit.framework.TestCase.assertEquals;

public final class CheckedFunctionTest {

    @Test
    public void shouldWrapTestFunction() {
        final Long longValue = 123456789L;
        final String longString = longValue.toString();
        final Function<String,Long> convertLongFunction =  CheckedFunction.wrap(CheckedFunctionTest::parseLong);
        assertEquals(longValue, convertLongFunction.apply(longString));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeException() {
        final Function<String,Long> convertLongFunction =  CheckedFunction.wrap(CheckedFunctionTest::parseLong);
        convertLongFunction.apply("NOT_NUMBER");
    }

    private static Long parseLong(String value) throws Exception {
        if(StringUtils.isBlank(value) || !value.matches("\\d+")){
            throw new Exception("Value is not number");
        }
        return Long.parseLong(value);
    }
}
