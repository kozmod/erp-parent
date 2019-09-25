package ru.aora.erp.repository.crud.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.repository.crud.CrudRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Repository
public class DbModuleRepository implements CrudRepository<DbModule, Long> {

    private static final String SELECT_ALL = "SELECT M.id,M.name FROM dbo.[Modules] M";
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE M.id = ? ";
    private static final String INSERT = "INSERT INTO dbo.[Modules] (name) VALUES (?) ";
    private static final String UPDATE = "UPDATE dbo.[Modules] SET name=? WHERE id = ? ";
    private static final String DELETE_BY_ID = "DELETE FROM dbo.[Modules] WHERE id= ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DbModuleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Collection<DbModule> findAll() {
        return jdbcTemplate.query(SELECT_ALL, new DbModuleExtractor());
    }

    @Override
    public Optional<DbModule> findById(long id) {
        final Collection<DbModule> users = jdbcTemplate.query(
                SELECT_BY_ID,
                new Object[]{id}, new int[]{Types.BIGINT},
                new DbModuleExtractor()
        );
        return Objects.isNull(users) || users.isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(users.iterator().next());
    }

    @Override
    public DbModule create(DbModule entity) {
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
        return entity.setId(
                requireNonNull(keyHolder.getKey(), "New created DbModule [id] must not be null").longValue()
        );
    }

    @Override
    public DbModule update(DbModule entity) {
        jdbcTemplate.update(
                UPDATE,
                entity.getName(),
                entity.getId()
        );
        return entity;
    }

    @Override
    public Long delete(Long id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
        return id;
    }

    @Transactional
    public void deleteBatch(List<DbModule> modules) {
        List<long[]> batch = new ArrayList<>();
        for (DbModule module : modules) {
            batch.add(new long[]{module.getId()});
        }
        jdbcTemplate.update(DELETE_BY_ID, batch);
    }

    private static class DbModuleExtractor implements ResultSetExtractor<Collection<DbModule>> {

        @Override
        public Collection<DbModule> extractData(@NonNull ResultSet rs) throws DataAccessException, SQLException {
            final List<DbModule> modules = new ArrayList<>();
            while (rs.next()) {
                final DbModule module = new DbModule();
                module.setId(rs.getLong("id"));
                module.setName(rs.getString("name"));
                modules.add(module);
            }
            return modules;
        }
    }
}
