package ru.aora.erp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.aora.erp.model.entity.db.DbCounteragent;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
@Transactional
public interface JpaCounteragentRepository extends JpaRepository<DbCounteragent, String> {

    @Query(value = "SELECT c FROM DbCounteragent c WHERE c.counteragentName = :name")
    Optional<DbCounteragent> findByName(String name);
}
