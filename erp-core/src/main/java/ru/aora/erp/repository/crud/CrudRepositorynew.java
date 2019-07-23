package ru.aora.erp.repository.crud;

import java.util.Collection;
import java.util.Optional;

public interface CrudRepositorynew<T> {

    Collection<T> findAll();

    Optional<T> findById(String id);

    void create(T entity);

    void update(T entity);

    void delete(String id);
}
