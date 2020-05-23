package ru.aora.erp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.aora.erp.model.entity.db.user.DbRole;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface JpaRoleRepository extends JpaRepository<DbRole, String> {

    @Query(value = "SELECT u FROM DbRole u WHERE u.name = :name")
    Optional<DbRole> findByName(String name);
}
