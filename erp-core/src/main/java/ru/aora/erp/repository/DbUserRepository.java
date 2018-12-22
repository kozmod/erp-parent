package ru.aora.erp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.model.entity.db.DbModuleRule;
import ru.aora.erp.model.entity.db.DbUser;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Repository
public class DbUserRepository {

    private static final String SELECT_ALL_USERS =
            "SELECT U.*, J.id_Module, M.name as name_Module, J.id_Rule, R.name as name_Rule FROM dbo.[Users] U (nolock) " +
                    "LEFT JOIN dbo.j_Users_Modules_Rule J (nolock) ON U.id = J.id_User   " +
                    "LEFT JOIN dbo.Modules              M (nolock) ON M.id = J.id_Module " +
                    "LEFT JOIN dbo.Modules_Access_Rules R (nolock) ON R.id = J.id_Rule   ";

    private static final String SELECT_USER_BY_NAME_JOIN_QUERY = SELECT_ALL_USERS + " WHERE U.user_name = ? ";

    private static final String SELECT_USER_BY_ID_QUERY = SELECT_ALL_USERS + " WHERE U.id = ? ";

    private static final String INSERT_USER =
            "INSERT INTO Users (user_name,password,first_name,surname,patronymic,phone_number,mail,employee_position,account_non_expired,account_non_locked,credentials_non_expired,enabled) " +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";


    private static final String INSERT_USER_LINK =
            "INSERT INTO j_Users_Modules_Rule (id_User, id_Module, id_Rule) " +
                    " VALUES (?, ?, ?) ";

    private static final String UPDATE_USER =
            "UPDATE Users  SET password=?, first_name=?, surname=?, patronymic=?, phone_number=?, mail=?, employee_position=?, account_non_expired=?,account_non_locked=?,credentials_non_expired=?,enabled=? " +
                    " WHERE id = ? ";

    private static final String DELETE_USER_BY_ID = "DELETE FROM Users WHERE id=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DbUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Collection<DbUser> findAll() {
        return jdbcTemplate.query(SELECT_ALL_USERS, new DbUserExtractor());
    }

    public Optional<DbUser> findByName(String name) throws SQLException {
        final Collection<DbUser> users = jdbcTemplate.query(
                SELECT_USER_BY_NAME_JOIN_QUERY,
                new Object[]{name}, new int[]{Types.VARCHAR},
                new DbUserExtractor()
        );
        return checkSearchResult(
                users,
                String.format("Incorrect query result. Fot user_name=[%s] was found [%s] users", name, users.size())
        );
    }

    public Optional<DbUser> findById(long id) throws SQLException {
        final Collection<DbUser> users = jdbcTemplate.query(
                SELECT_USER_BY_ID_QUERY,
                new Object[]{id}, new int[]{Types.BIGINT},
                new DbUserExtractor()
        );
        return checkSearchResult(
                users,
                String.format("Incorrect query result. Fot user_id=[%s] was found [%s] users", id, users.size())
        );
    }

    public long create(DbUser user) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        final int affectedRow = jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            INSERT_USER,
                            new String[]{"id"}
                    );
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, user.getFirstName());
                    ps.setString(4, user.getSurname());
                    ps.setString(5, user.getPatronymic());
                    ps.setString(6, user.getPhoneNumber());
                    ps.setString(7, user.getMail());
                    ps.setString(8, user.getEmployeePosition());
                    ps.setBoolean(9, user.isAccountNonExpired());
                    ps.setBoolean(10, user.isAccountNonLocked());
                    ps.setBoolean(11, user.isCredentialsNonExpired());
                    ps.setBoolean(12, user.isEnabled());
                    return ps;
                },
                keyHolder
        );
        final long newDbUserId = Objects.requireNonNull(
                keyHolder.getKey(),
                "New created DbUser [id] must not be null"
        ).longValue();
        if (Objects.equals(affectedRow, 1) && Objects.nonNull(keyHolder.getKey())) {
            for (DbModule module : user.getAuthorities()) {
                for (DbModuleRule rule : module.getModuleRoles()) {
                    jdbcTemplate.update(
                            INSERT_USER_LINK,
                            newDbUserId,
                            module.getId(),
                            rule.getId()
                    );
                }
            }
        }
        return newDbUserId;
    }

    public void update(DbUser user) {
        jdbcTemplate.update(
                UPDATE_USER,
                user.getPassword(),
                user.getFirstName(),
                user.getSurname(),
                user.getPatronymic(),
                user.getPhoneNumber(),
                user.getMail(),
                user.getEmployeePosition(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                user.getId()
        );
    }

    public void delete(long userId) {
        jdbcTemplate.update(DELETE_USER_BY_ID, userId);
    }

    private Optional<DbUser> checkSearchResult(Collection<DbUser> users, String exText) throws SQLException {
        if (Objects.isNull(users) || users.isEmpty()) {
            return Optional.empty();
        } else if (users.size() > 1) {
            throw new SQLException(exText);
        } else {
            return Optional.ofNullable(users.iterator().next());
        }
    }
}