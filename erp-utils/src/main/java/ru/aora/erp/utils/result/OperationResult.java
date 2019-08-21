package ru.aora.erp.utils.result;

import ru.aora.erp.utils.CheckedFunction;
import ru.aora.erp.utils.CheckedSupplier;

import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represents a result of operation. Contains the success and error result.
 *
 * @param <R> the type of the success result of operation
 * @param <F> the type of the error result of operation
 */
public final class OperationResult<R, F> {

    private final R result;
    private final F failure;

    private OperationResult(R result, F failure) {
        this.result = result;
        this.failure = failure;
    }

    public Optional<R> getResult() {
        return Optional.ofNullable(result);
    }

    public Optional<F> getFailure() {
        return Optional.ofNullable(failure);
    }

    public boolean isResult() {
        return result != null && failure == null;
    }

    public boolean isFailure() {
        return !isResult();
    }

    public static <T, R> Function<T, OperationResult<R, SourceExceptionPair<T>>> liftSource(CheckedFunction<T, R> func) {
        return t -> {
            try {
                return result(func.apply(t));
            } catch (Exception e) {
                return failure(SourceExceptionPair.of(t, e));
            }
        };
    }

    public static <T, R> Function<T, OperationResult<R, Exception>> lift(CheckedFunction<T, R> func) {
        return t -> {
            try {
                return result(func.apply(t));
            } catch (Exception ex) {
                return failure(ex);
            }
        };
    }

    public static <T> OperationResult<T, Exception> get(CheckedSupplier<T> supplier) {
        return lift(supplier).get();
    }

    public static <T> Supplier<OperationResult<T, Exception>> lift(CheckedSupplier<T> supplier) {
        return () -> {
            try {
                return result(supplier.get());
            } catch (Exception ex) {
                return failure(ex);
            }
        };
    }

    public static <R, F> OperationResult<R, F> result(R result) {
        return of(result, null);
    }

    public static <R, F> OperationResult<R, F> failure(F failure) {
        return of(null, failure);
    }

    private static <R, F> OperationResult<R, F> of(R result, F failure) {
        return new OperationResult<>(result, failure);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OperationResult.class.getSimpleName() + "[", "]")
                .add("result=" + result)
                .add("failure=" + failure)
                .toString();
    }
}
