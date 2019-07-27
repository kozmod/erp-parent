package ru.aora.erp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.aora.erp.model.entity.db.DbCounteragent;
import ru.aora.erp.model.entity.db.DbKs;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
@Transactional
public interface DbKsRepository extends JpaRepository<DbKs, Object> {
}
