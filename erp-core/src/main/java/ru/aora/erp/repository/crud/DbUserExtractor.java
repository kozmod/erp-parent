package ru.aora.erp.repository.crud;

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
                                            DbModuleRule.builder()
                                                    .withId(ruleId)
                                                    .withName(ruleName)
                                                    .build()
                                    );
                                }
                            }
                            , () -> user.getAuthorities().add(
                                    DbModule.builder()
                                            .withId(moduleId)
                                            .withName(moduleName)
                                            .withModuleRoles(
                                                    Set.of(
                                                            DbModuleRule.builder()
                                                                    .withId(ruleId)
                                                                    .withName(ruleName)
                                                                    .build()
                                                    )
                                            ).build()
                            )
                    );
        }
        return users.values();
    }

    private DbUser newUser(ResultSet rs) throws SQLException {
        return DbUser
                .builder()
                .withId(rs.getLong("id"))
                .withUsername(rs.getString("user_name"))
                .withPassword(rs.getString("password"))
                .withFirstName(rs.getString("first_name"))
                .withSurname(rs.getString("surname"))
                .withPatronymic(rs.getString("patronymic"))
                .withPhoneNumber(rs.getString("phone_number"))
                .withMail(rs.getString("mail"))
                .withEmployeePosition(rs.getString("employee_position"))
                .withAccountNonExpired(rs.getBoolean("account_non_expired"))
                .withAccountNonLocked(rs.getBoolean("account_non_locked"))
                .withCredentialsNonExpired(rs.getBoolean("credentials_non_expired"))
                .withEnabled(rs.getBoolean("enabled"))
                .withAuthorities(new HashSet<>())
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
