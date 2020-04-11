package ru.aora.erp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.aora.erp.model.entity.db.user.DbSubAuthority;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface JpaSubAuthorityRepository extends JpaRepository<DbSubAuthority, String> {

    @Query(value = "SELECT u FROM DbSubAuthority u WHERE u.name = :name")
    Optional<DbSubAuthority> findByName(String name);
}
