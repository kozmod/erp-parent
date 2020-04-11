package ru.aora.erp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.aora.erp.model.entity.db.user.DbAuthority;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface JpaAuthorityRepository extends JpaRepository<DbAuthority, String> {

    @Query(value = "SELECT u FROM DbAuthority u WHERE u.name = :name")
    Optional<DbAuthority> findByName(String name);

}
