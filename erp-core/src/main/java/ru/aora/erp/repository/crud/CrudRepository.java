package ru.aora.erp.repository.crud;

import java.util.Collection;
import java.util.Optional;

public interface CrudRepository<E,ID> {

    Collection<E> findAll();

    Optional<E> findById(long id);

    E create(E entity);

    E update(E entity);

    ID delete(ID id);
}
