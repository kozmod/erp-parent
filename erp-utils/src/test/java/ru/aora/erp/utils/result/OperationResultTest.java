package ru.aora.erp.utils.result;

import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public final class OperationResultTest {

    private static Long LONG_VALUE = 123456789L;
    private static String STRING_LONG_VALUE = LONG_VALUE.toString();
    private static String STRING_ERROR_LONG_VALUE = "NOT_NUMBER_STRING";

    @Test
    public void shouldSuccessResultWithExceptionPair() {
        final Function<String, OperationResult<Long, SourceExceptionPair<String>>> function = OperationResult.liftSource(Long::parseLong);
        final OperationResult<Long, SourceExceptionPair<String>> operationResult = function.apply(STRING_LONG_VALUE);

        assertNotNull(operationResult);
        assertTrue(operationResult.isLeft());
        assertTrue(operationResult.getLeft().isPresent());
        assertEquals(LONG_VALUE, operationResult.getLeft().get());
        assertTrue(operationResult.getRight().isEmpty());
    }

    @Test
    public void shouldGetExceptionWithExceptionPair() {
        final Function<String, OperationResult<Long, SourceExceptionPair<String>>> function = OperationResult.liftSource(Long::parseLong);
        final OperationResult<Long, SourceExceptionPair<String>> operationResult = function.apply(STRING_ERROR_LONG_VALUE);

        assertNotNull(operationResult);
        assertTrue(operationResult.getLeft().isEmpty());
        assertTrue(operationResult.isRight());
        assertTrue(operationResult.getRight().isPresent());
        assertNotNull(operationResult.getRight().get());
        assertNotNull(operationResult.getRight().get().getEntity());
        assertNotNull(operationResult.getRight().get().getException());
        assertEquals(STRING_ERROR_LONG_VALUE, operationResult.getRight().get().getEntity());
        assertEquals(NumberFormatException.class, operationResult.getRight().get().getException().getClass());
    }

    @Test
    public void shouldSuccessResult() {
        final Function<String, OperationResult<Long, Exception>> function = OperationResult.lift(Long::parseLong);
        final OperationResult<Long, Exception> operationResult = function.apply(STRING_LONG_VALUE);

        assertNotNull(operationResult);
        assertTrue(operationResult.isLeft());
        assertTrue(operationResult.getLeft().isPresent());
        assertEquals(LONG_VALUE, operationResult.getLeft().get());
        assertTrue(operationResult.getRight().isEmpty());
    }

    @Test
    public void shouldGetException() {
        final Function<String, OperationResult<Long, Exception>> function = OperationResult.lift(Long::parseLong);
        final OperationResult<Long, Exception> operationResult = function.apply(STRING_ERROR_LONG_VALUE);

        assertNotNull(operationResult);
        assertTrue(operationResult.getLeft().isEmpty());
        assertTrue(operationResult.getRight().isPresent());
        assertNotNull(operationResult.getRight().get());
        assertEquals(NumberFormatException.class, operationResult.getRight().get().getClass());
    }

    @Test
    public void shouldGetResultListWithOneException() {
        List<OperationResult<Long, Exception>> result = List.of(
                LONG_VALUE.toString(),
                STRING_ERROR_LONG_VALUE
        ).stream()
                .map(OperationResult.lift(Long::parseLong))
                .collect(Collectors.toList());

        assertNotNull(result);
        assertThat(result, hasSize(2));

        assertFalse(result.get(0).getRight().isPresent());
        assertTrue(result.get(0).getLeft().isPresent());
        assertEquals(LONG_VALUE,result.get(0).getLeft().get());

        assertTrue(result.get(1).getRight().isPresent());
        assertFalse(result.get(1).getLeft().isPresent());
        assertEquals(NumberFormatException.class,result.get(1).getRight().get().getClass());
    }
}
