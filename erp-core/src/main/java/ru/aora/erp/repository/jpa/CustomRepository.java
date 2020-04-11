package ru.aora.erp.repository.jpa;

public interface CustomRepository<T> {
    void deactivateAllByUuid(T entity);
    void deactivateById(String uuid);
}
