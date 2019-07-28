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
 * @param <L> the type of the success result of operation
 * @param <R> the type of the error result of operation
 */
public final class OperationResult<L, R> {

    private final L left;
    private final R right;

    private OperationResult(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public Optional<L> getLeft() {
        return Optional.ofNullable(left);
    }

    public Optional<R> getRight() {
        return Optional.ofNullable(right);
    }

    public boolean isLeft() {
        return left != null && right == null;
    }

    public boolean isRight() {
        return !isLeft();
    }

    public static <T, R> Function<T, OperationResult<R, SourceExceptionPair<T>>> liftSource(CheckedFunction<T, R> func) {
        return t -> {
            try {
                return OperationResult.left(func.apply(t));
            } catch (Exception e) {
                return OperationResult.right(SourceExceptionPair.of(t, e));
            }
        };
    }

    public static <T, R> Function<T, OperationResult<R, Exception>> lift(CheckedFunction<T, R> func) {
        return t -> {
            try {
                return OperationResult.left(func.apply(t));
            } catch (Exception ex) {
                return OperationResult.right(ex);
            }
        };
    }

    public static <T> Supplier<OperationResult<T, Exception>> lift(CheckedSupplier<T> supplier) {
        return () -> {
            try {
                return OperationResult.left(supplier.get());
            } catch (Exception ex) {
                return OperationResult.right(ex);
            }
        };
    }

    public static <L, R> OperationResult<L, R> left(L left) {
        return OperationResult.of(left, null);
    }

    public static <L, R> OperationResult<L, R> right(R right) {
        return OperationResult.of(null, right);
    }

    public static <L, R> OperationResult<L, R> of(L left, R right) {
        return new OperationResult<>(left, right);
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", OperationResult.class.getSimpleName() + "[", "]")
                .add("left=" + left)
                .add("right=" + right)
                .toString();
    }
}
