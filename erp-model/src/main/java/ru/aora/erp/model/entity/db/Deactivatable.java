package ru.aora.erp.model.entity.db;

import java.time.LocalDateTime;

public interface Deactivatable {

    Integer INACTIVE_ENTITY_FLAG = 1;
    Integer ACTIVE_ENTITY_FLAG = 0;

    Integer getActiveStatus();

    LocalDateTime getDeactivationDate();

    default boolean isActive() {
        return ACTIVE_ENTITY_FLAG.equals(this.getActiveStatus()) && this.getDeactivationDate() == null;
    }

}
