package ru.aora.erp.repository.crud.counteragent;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.NonNull;
import ru.aora.erp.model.entity.db.DbCounteragent;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.model.entity.db.DbModuleRule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DbCounteragentExtractor implements ResultSetExtractor<Collection<DbCounteragent>> {

    @Override
    public Collection<DbCounteragent> extractData(@NonNull ResultSet rs) throws DataAccessException, SQLException {
        final Map<String, DbCounteragent> counteragents = new HashMap<>();

        while (rs.next()) {
            final String id = rs.getString("id_counteragent");
            final var counteragent = counteragents.getOrDefault(
                    id,
                    counteragents.put(id, newCounteragent(rs))
            );

        }
        return counteragents.values();
    }

    private DbCounteragent newCounteragent(ResultSet rs) throws SQLException {
        return new DbCounteragent()
                .setId(rs.getString("id_counteragent"))
                .setCounteragentName(rs.getString("counteragent_name"))
                .setGroupName(rs.getString("group_name"))
                .setDirectorFirstName(rs.getString("first_name"))
                .setDirectorSurname(rs.getString("surname"))
                .setDirectorPatronymic(rs.getString("patronymic"))
                .setPhoneNumber(rs.getString("phone_number"))
                .setMail(rs.getString("mail"))
                .setAddress(rs.getString("address"));
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