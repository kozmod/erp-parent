package ru.aora.erp.repository.crud;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.NonNull;
import ru.aora.erp.model.entity.db.DbKs;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.model.entity.db.DbModuleRule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DbKsExtractor implements ResultSetExtractor<Collection<DbKs>> {

    @Override
    public Collection<DbKs> extractData(@NonNull ResultSet rs) throws DataAccessException, SQLException {
        final Map<String, DbKs> kss = new HashMap<>();

        while (rs.next()) {
            final String id = rs.getString("id_KS");
            final var ks = kss.getOrDefault(
                    id,
                    kss.put(id, newKs(rs))
            );

        }
        return kss.values();
    }

    private DbKs newKs(ResultSet rs) throws SQLException {
        return DbKs
                .builder()
                .withId(rs.getString("id_KS"))
                .withContractId(rs.getString("id_contract"))
                .withKsDate(rs.getString("KS_date"))
                .withKsNumber(rs.getString("KS_number"))
                .withKsSum(rs.getBigDecimal("KS_sum"))
                .withGarantSum(rs.getBigDecimal("Garant_sum"))
                .withGarantDate(rs.getString("Garant_date"))
                .build();
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
