package ru.aora.erp.domain;


import java.util.List;
import java.util.Optional;

public interface CrudGateway<E, ID> {

    List<E> loadAllActive();

    Optional<E> getById(ID id);

    E create(E entity);

    Optional<E> update(E entity);

    Optional<E> delete(ID id);
}
