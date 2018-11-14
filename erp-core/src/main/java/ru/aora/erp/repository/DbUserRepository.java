package ru.aora.erp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.model.entity.db.DbModuleRule;
import ru.aora.erp.model.entity.db.DbUser;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Repository
public class DbUserRepository {

    private static final String SELECT_USER_BY_NAME_JOIN_QUERY =
            "SELECT U.*, J.id_Module, M.name as name_Module, J.id_Rule, R.name as name_Rule FROM dbo.[Users] U (nolock)" +
                    "JOIN dbo.j_Users_Modules_Rule J (nolock) ON U.id = J.id_User   " +
                    "JOIN dbo.Modules              M (nolock) ON M.id = J.id_Module " +
                    "JOIN dbo.Modules_Access_Rules R (nolock) ON R.id = J.id_Rule   " +
                    "WHERE U.user_name = ?";

    private static final String INSERT_USER =
            "INSERT INTO Users (user_name, password ,phone_number, mail, account_non_expired,account_non_locked,credentials_non_expired,enabled) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

    private static final String INSERT_USER_LINK =
            "INSERT INTO j_Users_Modules_Rule (id_User, id_Module, id_Rule) " +
                    "VALUES (?, ?, ?) ";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DbUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<DbUser> findByName(String name) {
        final var rs = jdbcTemplate.queryForRowSet(SELECT_USER_BY_NAME_JOIN_QUERY, name);
        DbUser user = null;
        while (rs.next()) {
            if (user == null) {
                user = newUser(rs);
            }
            final var moduleId = rs.getLong("id_Module");
            final var moduleName = rs.getString("name_Module");
            final var ruleId = rs.getLong("id_Rule");
            final var ruleName = rs.getString("name_Rule");

            Optional<DbModule> module = tryFindModule(moduleId, moduleName, user.getAuthorities());
            if (module.isPresent()) {
                if (tryFindRule(ruleId, ruleName, module.get().getModuleRoles()).isEmpty()) {
                    module.get().getModuleRoles().add(
                            DbModuleRule.builder()
                                    .withId(ruleId)
                                    .withName(ruleName)
                                    .build()
                    );
                }
            } else {
                user.getAuthorities().add(
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
                );
            }
        }
        return Optional.ofNullable(user);
    }


    public void save(DbUser user) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        final int affectedRow = jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            INSERT_USER,
                            new String[]{"id"}
                    );
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, user.getPhoneNumber());
                    ps.setString(4, user.getMail());
                    ps.setBoolean(5, user.isAccountNonExpired());
                    ps.setBoolean(6, user.isAccountNonLocked());
                    ps.setBoolean(7, user.isCredentialsNonExpired());
                    ps.setBoolean(8, user.isEnabled());
                    return ps;
                },
                keyHolder
        );
        if (Objects.equals(affectedRow, 1) && Objects.nonNull(keyHolder.getKey())) {
            for (DbModule module : user.getAuthorities()) {
                for (DbModuleRule rule : module.getModuleRoles()) {
                    jdbcTemplate.update(
                            INSERT_USER_LINK,
                            keyHolder.getKey(),
                            module.getId(),
                            rule.getId()
                    );
                }
            }
        }
    }

    private DbUser newUser(SqlRowSet rs) {
        final var user = new DbUser();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("user_name"));
        user.setPassword(rs.getString("password"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setMail(rs.getString("mail"));
        user.setAccountNonExpired(rs.getBoolean("account_non_expired"));
        user.setAccountNonLocked(rs.getBoolean("account_non_locked"));
        user.setCredentialsNonExpired(rs.getBoolean("credentials_non_expired"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setAuthorities(new HashSet<>());
        return user;
    }

    private DbModule newModule(SqlRowSet rs) {
        final var module = new DbModule();
        module.setId(rs.getInt("id"));
        module.setName(rs.getString("name_Module"));
        module.setModuleRoles(new HashSet<>());
        return module;
    }

    private Optional<DbModule> tryFindModule(long id, String moduleName, Collection<DbModule> modules) {
        for (var module : modules) {
            if (module.getName().equals(moduleName) && Objects.equals(id, module.getId())) {
                return Optional.of(module);
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