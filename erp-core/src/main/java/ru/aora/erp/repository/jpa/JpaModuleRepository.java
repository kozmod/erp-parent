package ru.aora.erp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.aora.erp.model.entity.db.user.DbModule;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface JpaModuleRepository extends JpaRepository<DbModule, String> {

    @Query(value = "SELECT u FROM DbModule u WHERE u.name = :name")
    Optional<DbModule> findByName(String name);

}
