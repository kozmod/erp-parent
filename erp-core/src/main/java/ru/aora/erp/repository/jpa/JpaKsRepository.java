package ru.aora.erp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aora.erp.model.entity.db.DbKs;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface JpaKsRepository extends JpaRepository<DbKs, String> {
}
