package ru.aora.erp.repository.crud.contract;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.NonNull;
import ru.aora.erp.model.entity.db.DbContract;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.model.entity.db.DbModuleRule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DbContractExtractor implements ResultSetExtractor<Collection<DbContract>> {

    @Override
    public Collection<DbContract> extractData(@NonNull ResultSet rs) throws DataAccessException, SQLException {
        final Map<String, DbContract> contracts = new HashMap<>();

        while (rs.next()) {
            final String id = rs.getString("id_contract");
            final var contract = contracts.getOrDefault(
                    id,
                    contracts.put(id, newContract(rs))
            );

        }
        return contracts.values();
    }

    private DbContract newContract(ResultSet rs) throws SQLException {
        return new DbContract()
                .setId(rs.getString("id_contract"))
                .setCounteragentId(rs.getString("id_counteragent"))
                .setContractType(rs.getInt("idTypeAgreement"))
                .setContractDate(rs.getString("contract_date"))
                .setContractNumber(rs.getString("contract_number"))
                .setContractSubject(rs.getString("contract_subject"));
    }

    private Optional<DbModule> tryFindModule(long id, String moduleName, Collection<DbModule> modules) {
        for (var dbModule : modules) {
            if (dbModule.getName().equals(moduleName) && Objects.equals(id, dbModule.getId())) {
                return Optional.of(dbModule);
            }
        }
        return Optional.empty();
    }

    private Optional<DbModuleRule> tryFindRule(long id, String ruleName, Collection<DbModuleRule> rules) {
        for (var rule : rules) {
            if (rule.getName().equals(ruleName) && Objects.equals(id, rule.getId())) {
                return Optional.of(rule);
            }
        }
        return Optional.empty();
    }
}
