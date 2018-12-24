package ru.aora.erp.repository.crud;

import java.util.Collection;
import java.util.Optional;

public interface CrudRepository<T> {

    Collection<T> findAll();

    Optional<T> findById(long id);

    long create(T entity);

    void update(T entity);

    void delete(long id);
}
