package ru.aora.erp.repository.jpa;

import ru.aora.erp.model.entity.db.TechnicalEntity;

public interface CustomRepository<T> {
    void deactivateAllByUuid(T entity);
    void deactivateById(String uuid);
}
