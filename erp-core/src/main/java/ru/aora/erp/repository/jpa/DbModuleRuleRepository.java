package ru.aora.erp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aora.erp.model.entity.db.DbModuleRule;

@Repository
public interface DbModuleRuleRepository extends JpaRepository<DbModuleRule, Long> {
}
