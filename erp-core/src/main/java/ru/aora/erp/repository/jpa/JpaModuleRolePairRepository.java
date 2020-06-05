package ru.aora.erp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.aora.erp.model.entity.db.user.DbModuleRolePair;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface JpaModuleRolePairRepository extends JpaRepository<DbModuleRolePair, String> {

    @Query(value = "SELECT u FROM DbModuleRolePair u WHERE u.userId = :userId")
    List<DbModuleRolePair> findByUserId(String userId);

    @Query(value = "SELECT u FROM DbModuleRolePair u WHERE u.userId = :userId AND u.deactivationDate IS NULL")
    List<DbModuleRolePair> findAllActiveByUserId(String userId);
}
