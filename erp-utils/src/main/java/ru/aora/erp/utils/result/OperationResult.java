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
                return OperationResult.result(func.apply(t));
            } catch (Exception e) {
                return OperationResult.failure(SourceExceptionPair.of(t, e));
            }
        };
    }

    public static <T, R> Function<T, OperationResult<R, Exception>> lift(CheckedFunction<T, R> func) {
        return t -> {
            try {
                return OperationResult.result(func.apply(t));
            } catch (Exception ex) {
                return OperationResult.failure(ex);
            }
        };
    }

    public static <T> OperationResult<T, Exception> get(CheckedSupplier<T> supplier) {
        return lift(supplier).get();
    }

    public static <T> Supplier<OperationResult<T, Exception>> lift(CheckedSupplier<T> supplier) {
        return () -> {
            try {
                return OperationResult.result(supplier.get());
            } catch (Exception ex) {
                return OperationResult.failure(ex);
            }
        };
    }

    public static <L, R> OperationResult<L, R> result(L left) {
        return OperationResult.of(left, null);
    }

    public static <L, R> OperationResult<L, R> failure(R right) {
        return OperationResult.of(null, right);
    }

    public static <L, R> OperationResult<L, R> of(L left, R right) {
        return new OperationResult<>(left, right);
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", OperationResult.class.getSimpleName() + "[", "]")
                .add("result=" + result)
                .add("failure=" + failure)
                .toString();
    }
}
