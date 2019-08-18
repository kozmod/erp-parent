package ru.aora.erp.utils;

import java.util.function.Supplier;

@FunctionalInterface
public interface CheckedSupplier<T> {

    T get() throws Exception;

    static <T> Supplier<T> wrap(CheckedSupplier<T> supplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
