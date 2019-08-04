package ru.aora.erp.repository.crud.user;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.NonNull;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.model.entity.db.DbModuleRule;
import ru.aora.erp.model.entity.db.DbUser;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class DbUserExtractor implements ResultSetExtractor<Collection<DbUser>> {

    @Override
    public Collection<DbUser> extractData(@NonNull ResultSet rs) throws DataAccessException, SQLException {
        final Map<Long, DbUser> users = new HashMap<>();

        while (rs.next()) {
            final long id = rs.getLong("id");
            final var user = users.getOrDefault(
                    id,
                    users.put(id, newUser(rs))
            );
            final var moduleId = rs.getLong("id_Module");
            final var moduleName = rs.getString("name_Module");
            final var ruleId = rs.getLong("id_Rule");
            final var ruleName = rs.getString("name_Rule");

            tryFindModule(moduleId, moduleName, user.getAuthorities())
                    .ifPresentOrElse(
                            mdbModule -> {
                                if (tryFindRule(ruleId, ruleName, mdbModule.getModuleRoles()).isEmpty()) {
                                    mdbModule.getModuleRoles().add(
                                            new DbModuleRule()
                                                    .setId(ruleId)
                                                    .setName(ruleName)
                                    );
                                }
                            }
                            , () -> user.getAuthorities().add(
                                    new DbModule()
                                            .setId(moduleId)
                                            .setName(moduleName)
                                            .setModuleRoles(
                                                    Set.of(
                                                            new DbModuleRule()
                                                                    .setId(ruleId)
                                                                    .setName(ruleName)
                                                    )
                                            )
                            )
                    );
        }
        return users.values();
    }

    private DbUser newUser(ResultSet rs) throws SQLException {
        return new DbUser()
                .setId(rs.getLong("id"))
                .setUsername(rs.getString("user_name"))
                .setPassword(rs.getString("password"))
                .setFirstName(rs.getString("first_name"))
                .setSurname(rs.getString("surname"))
                .setPatronymic(rs.getString("patronymic"))
                .setPhoneNumber(rs.getString("phone_number"))
                .setMail(rs.getString("mail"))
                .setEmployeePosition(rs.getString("employee_position"))
                .setAccountNonExpired(rs.getBoolean("account_non_expired"))
                .setAccountNonLocked(rs.getBoolean("account_non_locked"))
                .setCredentialsNonExpired(rs.getBoolean("credentials_non_expired"))
                .setEnabled(rs.getBoolean("enabled"))
                .setAuthorities(new HashSet<>());
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
