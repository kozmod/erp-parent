package ru.aora.erp.repository.crud.counteragent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aora.erp.model.entity.db.DbCounteragent;
import ru.aora.erp.repository.crud.CrudRepositorynew;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@Repository
@Transactional
public class DbCounteragentRepository implements CrudRepositorynew<DbCounteragent> {

    private static final String SELECT_ALL_COUNTERAGENTS =
            "SELECT id_counteragent,U.counteragent_name,U.group_name,U.first_name,U.surname,U.patronymic,U.phone_number,U.mail,U.address FROM dbo.[Counteragents] U (nolock) ";

    private static final String SELECT_COUNTERAGENT_BY_NAME_JOIN_QUERY = SELECT_ALL_COUNTERAGENTS + " WHERE U.counteragent_name = ? ";

    private static final String SELECT_COUNTERAGENT_BY_ID_QUERY = SELECT_ALL_COUNTERAGENTS + " WHERE U.id_counteragent = ? ";

    private static final String INSERT_COUNTERAGENT =
            "INSERT INTO dbo.Counteragents with(rowlock)(counteragent_name,group_name,first_name,surname,patronymic,phone_number,mail,address)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

    private static final String UPDATE_COUNTERAGENT =
            "UPDATE Counteragents SET counteragent_name=?, group_name=?, first_name=?, surname=?, patronymic=?, phone_number=?, mail=?, address=? " +
                    " WHERE id_counteragent =? ";

    private static final String DELETE_COUNTERAGENT_BY_ID = "DELETE FROM Counteragents WHERE id_counteragent=?";


    private JdbcTemplate jdbcTemplate;

    public String newDbCounteragentId;

    @Autowired
    public DbCounteragentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Collection<DbCounteragent> findAll() {
        return jdbcTemplate.query(SELECT_ALL_COUNTERAGENTS, new DbCounteragentExtractor());
    }

    public Optional<DbCounteragent> findByName(String name) throws SQLException {
        final Collection<DbCounteragent> counteragents = jdbcTemplate.query(
                SELECT_COUNTERAGENT_BY_NAME_JOIN_QUERY,
                new Object[]{name}, new int[]{Types.VARCHAR},
                new DbCounteragentExtractor()
        );
        if (Objects.isNull(counteragents) || counteragents.isEmpty()) {
            return Optional.empty();
        } else if (counteragents.size() > 1) {
            throw new SQLException(
                    String.format("Incorrect query result. For counteragent_name=[%s] was found [%s] counteragents", name, counteragents.size())
            );
        } else {
            return Optional.ofNullable(counteragents.iterator().next());
        }
    }

    @Override
    public Optional<DbCounteragent> findById(String id) {
        final Collection<DbCounteragent> counteragents = jdbcTemplate.query(
                SELECT_COUNTERAGENT_BY_ID_QUERY,
                new Object[]{id}, new int[]{Types.BIGINT},
                new DbCounteragentExtractor()
        );
        return Objects.isNull(counteragents) || counteragents.isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(counteragents.iterator().next());
    }

    @Override
    public void create(DbCounteragent counteragent) {
    /*    final KeyHolder keyHolder = new GeneratedKeyHolder();
        final int affectedRow = jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            INSERT_COUNTERAGENT,
                            new String[]{"id"}
                    );
                    ps.setString(1, counteragent.getCounteragentName());
                    ps.setString(2, counteragent.getGroupName());
                    ps.setString(3, counteragent.getDirectorFirstName());
                    ps.setString(4, counteragent.getDirectorSurname());
                    ps.setString(5, counteragent.getDirectorPatronymic());
                    ps.setString(6, counteragent.getPhoneNumber());
                    ps.setString(7, counteragent.getMail());
                    ps.setString(8, counteragent.getAddress());

                    return ps;
                },
                keyHolder
        );
        final String newDbCounteragentId = Objects.requireNonNull(
                keyHolder.getKey(),
                "New created DbCounteragent [id] must not be null"
        ).toString();

        return newDbCounteragentId;*/
    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "db.properties";

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(appProps.getProperty("jdbc.user"));
        dataSource.setPassword(appProps.getProperty("jdbc.pass"));
        dataSource.setUrl(appProps.getProperty("users.jdbc.url"));
        dataSource.setDriverClassName(
                Objects.requireNonNull(appProps.getProperty("jdbc.driverClassName"))
        );
        Connection connection;
        connection = DataSourceUtils.getConnection(dataSource);
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_COUNTERAGENT);
            statement.setString(1, counteragent.getCounteragentName());
            statement.setString(2, counteragent.getGroupName());
            statement.setString(3, counteragent.getDirectorFirstName());
            statement.setString(4, counteragent.getDirectorSurname());
            statement.setString(5, counteragent.getDirectorPatronymic());
            statement.setString(6, counteragent.getPhoneNumber());
            statement.setString(7, counteragent.getMail());
            statement.setString(8, counteragent.getAddress());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(DbCounteragent counteragent) throws DataAccessException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "db.properties";

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(appProps.getProperty("jdbc.user"));
        dataSource.setPassword(appProps.getProperty("jdbc.pass"));
        dataSource.setUrl(appProps.getProperty("users.jdbc.url"));
        dataSource.setDriverClassName(
                Objects.requireNonNull(appProps.getProperty("jdbc.driverClassName"))
        );
        Connection connection;
        connection = DataSourceUtils.getConnection(dataSource);
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_COUNTERAGENT);
            statement.setString(1, counteragent.getCounteragentName());
            statement.setString(2, counteragent.getGroupName());
            statement.setString(3, counteragent.getDirectorFirstName());
            statement.setString(4, counteragent.getDirectorSurname());
            statement.setString(5, counteragent.getDirectorPatronymic());
            statement.setString(6, counteragent.getPhoneNumber());
            statement.setString(7, counteragent.getMail());
            statement.setString(8, counteragent.getAddress());
            statement.setObject(9, UUID.fromString(counteragent.getId()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String counteragentId) {
        jdbcTemplate.update(DELETE_COUNTERAGENT_BY_ID, counteragentId);
    }
}
