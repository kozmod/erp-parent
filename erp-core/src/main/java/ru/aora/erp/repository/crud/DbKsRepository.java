package ru.aora.erp.repository.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aora.erp.model.entity.db.DbKs;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@Repository
@Transactional
public class DbKsRepository implements CrudRepositorynew<DbKs> {

    private static final String SELECT_ALL_KS =
            "SELECT id_KS,id_contract,U.KS_date,U.Ks_number,U.KS_sum,U.Garant_sum,U.Garant_date FROM dbo.[KS] U (nolock) ";

    private static final String SELECT_KS_BY_NAME_JOIN_QUERY = SELECT_ALL_KS + " WHERE U.KS_number = ? ";

    private static final String SELECT_KS_BY_ID_QUERY = SELECT_ALL_KS + " WHERE U.id_KS = ? ";

    private static final String INSERT_KS =
            "INSERT INTO dbo.KS with(rowlock)(id_contract,KS_date,KS_number,KS_sum,Garant_sum,Garant_date)" +
                    " VALUES (?, ?, ?, ?, ?,?) ";

    private static final String UPDATE_KS =
            "UPDATE KS SET KS_date=?,KS_number=?,KS_sum=?,Garant_sum=?,Garant_date=? " +
                    " WHERE id_KS =? ";

    private static final String DELETE_KS_BY_ID = "DELETE FROM KS WHERE id_KS=?";


    private JdbcTemplate jdbcTemplate;

    public String newDbKsId;

    @Autowired
    public DbKsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Collection<DbKs> findAll() {
        return jdbcTemplate.query(SELECT_ALL_KS, new DbKsExtractor());
    }

    public Optional<DbKs> findByName(String name) throws SQLException {
        final Collection<DbKs> kss = jdbcTemplate.query(
                SELECT_KS_BY_NAME_JOIN_QUERY,
                new Object[]{name}, new int[]{Types.VARCHAR},
                new DbKsExtractor()
        );
        if (Objects.isNull(kss) || kss.isEmpty()) {
            return Optional.empty();
        } else if (kss.size() > 1) {
            throw new SQLException(
                    String.format("Incorrect query result. For ks_name=[%s] was found [%s] kss", name, kss.size())
            );
        } else {
            return Optional.ofNullable(kss.iterator().next());
        }
    }

    @Override
    public Optional<DbKs> findById(String id) {
        final Collection<DbKs> kss = jdbcTemplate.query(
                SELECT_KS_BY_ID_QUERY,
                new Object[]{id}, new int[]{Types.BIGINT},
                new DbKsExtractor()
        );
        return Objects.isNull(kss) || kss.isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(kss.iterator().next());
    }

    @Override
    public void create(DbKs ks) {
    /*    final KeyHolder keyHolder = new GeneratedKeyHolder();
        final int affectedRow = jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            INSERT_CONTRACT,
                            new String[]{"id"}
                    );
                    ps.setString(1, ks.getContractName());
                    ps.setString(2, ks.getGroupName());
                    ps.setString(3, ks.getDirectorFirstName());
                    ps.setString(4, ks.getDirectorSurname());
                    ps.setString(5, ks.getDirectorPatronymic());
                    ps.setString(6, ks.getPhoneNumber());
                    ps.setString(7, ks.getMail());
                    ps.setString(8, ks.getAddress());

                    return ps;
                },
                keyHolder
        );
        final String newDbKsId = Objects.requireNonNull(
                keyHolder.getKey(),
                "New created DbKs [id] must not be null"
        ).toString();

        return newDbKsId;*/
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
            System.out.println("final "+ks);
            PreparedStatement statement = connection.prepareStatement(INSERT_KS);
            statement.setObject(1, UUID.fromString(ks.getContractId()));
            statement.setString(2, ks.getKsDate());
            statement.setString(3, ks.getKsNumber());
            statement.setBigDecimal(4, ks.getKsSum());
            statement.setBigDecimal(5, ks.getGarantSum());
            statement.setString(6, ks.getGarantDate());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(DbKs ks) throws DataAccessException {
        System.out.println(ks);
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

            PreparedStatement statement = connection.prepareStatement(UPDATE_KS);
            statement.setString(1, ks.getKsDate());
            statement.setString(2, ks.getKsNumber());
            statement.setBigDecimal(3, ks.getKsSum());
            statement.setBigDecimal(4, ks.getGarantSum());
            statement.setString(5, ks.getGarantDate());
            statement.setObject(6, UUID.fromString(ks.getId()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String KsId) {
        jdbcTemplate.update(DELETE_KS_BY_ID, KsId);
    }
}
