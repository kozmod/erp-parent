package ru.aora.erp.model.entity.db;

import java.time.LocalDateTime;

public interface TechnicalEntity<T> {

    LocalDateTime getCreationDate();

    T setCreationDate(LocalDateTime creationDate) ;

    LocalDateTime getDeactivationDate();

    T setDeactivationDate(LocalDateTime deactivationDate);

    String getEntityUuid();

    T setEntityUuid(String entityUuid);
}
