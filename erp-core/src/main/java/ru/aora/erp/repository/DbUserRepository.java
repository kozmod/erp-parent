package ru.aora.erp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.model.entity.db.DbUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Repository
public class DbUserRepository {

    private static final String SELECT_USER_BY_NAME_JOIN_QUERY =
            "SELECT U.*, J.id_Module, M.name as name_Module, j.id_rule, R.name as name_Rule FROM dbo.[Users] U (nolock)" +
                    "JOIN dbo.j_Users_Modules_Rule J (nolock) ON U.id = J.id_User   " +
                    "JOIN dbo.Modules              M (nolock) ON M.id = J.id_Module " +
                    "JOIN dbo.Modules_access_rules R (nolock) ON R.id = J.id_rule   " +
                    "WHERE U.user_name = ?";

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
            final var moduleName = rs.getString("name_Module");
            final var moduleRule = rs.getString("name_Rule");
            Optional<DbModule> module = tryFindModule(moduleName, user.getAuthorities());
            if (module.isPresent()) {
                module.get().getModuleRoles().add(moduleRule);
            } else {
                final var newModule = newModule(rs);
                newModule.getModuleRoles().add(moduleRule);
                user.getAuthorities().add(newModule);
            }
        }
        return Optional.ofNullable(user);
    }

    private DbUser newUser(SqlRowSet rs) {
        final var user = new DbUser();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("user_name"));
        user.setPassword(rs.getString("password"));
        user.setMail(rs.getString("mail"));
        user.setAccountNonExpired(rs.getBoolean("account_non_expired"));
        user.setAccountNonLocked(rs.getBoolean("account_non_locked"));
        user.setCredentialsNonExpired(rs.getBoolean("credentials_non_expired"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setAuthorities(new ArrayList<>());
        return user;
    }

    private DbModule newModule(SqlRowSet rs) {
        final var module = new DbModule();
        module.setId(rs.getInt("id"));
        module.setName(rs.getString("name_Module"));
        module.setModuleRoles(new HashSet<>());
        return module;
    }

    private Optional<DbModule> tryFindModule(String moduleName, Collection<DbModule> modules) {
        for (var module : modules) {
            if (module.getName().equals(moduleName)) {
                return Optional.of(module);
            }
        }
        return Optional.empty();

    }
}