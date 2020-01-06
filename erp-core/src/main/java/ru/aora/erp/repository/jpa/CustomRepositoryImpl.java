package ru.aora.erp.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import ru.aora.erp.model.entity.db.TechnicalEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;


public class CustomRepositoryImpl<T extends TechnicalEntity<T>> implements CustomRepository<T> {

    private EntityManager entityManager;

    @Autowired
    public CustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void deactivateAllByUuid(T entity) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        Class<T> c = (Class<T>) entity.getClass();
        // create update
        CriteriaUpdate<?> update = cb.createCriteriaUpdate(c);

        // set the root class
        Root<T> e = (Root<T>) update.from(c);

        // set update and where clause
        update.set("deactivation_date", LocalDateTime.now());
        update.where(cb.equal(e.get("uuid"), entity.getEntityUuid()));

        // perform update
        entityManager.createQuery(update).executeUpdate();
    }

    @Override
    public void deactivateById(String uuid) {

    }
}
