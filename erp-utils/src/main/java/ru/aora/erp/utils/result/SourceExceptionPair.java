package ru.aora.erp.utils.result;

import java.util.StringJoiner;

public final class SourceExceptionPair<T> {

    private final T entity;
    private final Exception exception;

    public SourceExceptionPair(T entity, Exception exception) {
        this.entity = entity;
        this.exception = exception;
    }

    public static <T> SourceExceptionPair<T> of(T entity, Exception exception){
        return new SourceExceptionPair<>(entity,exception);
    }

    public T getEntity() {
        return entity;
    }

    public Exception getException() {
        return exception;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SourceExceptionPair.class.getSimpleName() + "[", "]")
                .add("entity=" + entity)
                .add("exception=" + exception)
                .toString();
    }
}
