package ru.aora.erp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.model.entity.db.DbModuleRule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class DbModuleRuleRepository implements CrudRepository<DbModuleRule> {

    private static final String SELECT_ALL = "SELECT R.id,R.name FROM dbo.[Modules_Access_Rules] R";
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE R.id = ? ";
    private static final String INSERT = "INSERT INTO dbo.[Modules_Access_Rules] (name) VALUES (?) ";
    private static final String UPDATE = "UPDATE dbo.[Modules_Access_Rules] SET name=? WHERE id = ? ";
    private static final String DELETE_BY_ID = "DELETE FROM dbo.[Modules_Access_Rules] WHERE id=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DbModuleRuleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Collection<DbModuleRule> findAll() {
        return jdbcTemplate.query(SELECT_ALL, new DbModuleRuleExtractor());
    }

    @Override
    public Optional<DbModuleRule> findById(long id) {
        final Collection<DbModuleRule> users = jdbcTemplate.query(
                SELECT_BY_ID,
                new Object[]{id}, new int[]{Types.BIGINT},
                new DbModuleRuleExtractor()
        );
        return Objects.isNull(users) || users.isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(users.iterator().next());
    }

    @Override
    public long create(DbModuleRule entity) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            INSERT,
                            new String[]{"id"}
                    );
                    ps.setString(1, entity.getName());
                    return ps;
                },
                keyHolder
        );
        return Objects.requireNonNull(keyHolder.getKey(), "New created DbModuleRule [id] must not be null").longValue();
    }

    @Override
    public void update(DbModuleRule entity) {
        jdbcTemplate.update(
                UPDATE,
                entity.getName(),
                entity.getId()
        );
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    private static class DbModuleRuleExtractor implements ResultSetExtractor<Collection<DbModuleRule>> {

        @Override
        public Collection<DbModuleRule> extractData(@NonNull ResultSet rs) throws DataAccessException, SQLException {
            final List<DbModuleRule> rules = new ArrayList<>();
            while (rs.next()) {
                final DbModuleRule module = new DbModuleRule();
                module.setId(rs.getLong("id"));
                module.setName(rs.getString("name"));
                rules.add(module);
            }
            return rules;
        }
    }
}
