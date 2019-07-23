package ru.aora.erp.repository.crud.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aora.erp.model.entity.db.DbContract;
import ru.aora.erp.repository.crud.CrudRepositorynew;
import ru.aora.erp.repository.crud.contract.DbContractExtractor;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@Repository
@Transactional
public class DbContractRepository implements CrudRepositorynew<DbContract> {

    private static final String SELECT_ALL_CONTRACTS =
            "SELECT id_contract,id_counteragent,U.idTypeAgreement,U.contract_date,U.contract_number,U.contract_subject FROM dbo.[Contract] U (nolock) ";

    private static final String SELECT_CONTRACT_BY_NAME_JOIN_QUERY = SELECT_ALL_CONTRACTS + " WHERE U.contract_number = ? ";

    private static final String SELECT_CONTRACT_BY_ID_QUERY = SELECT_ALL_CONTRACTS + " WHERE U.id_contract = ? ";

    private static final String INSERT_CONTRACT =
            "INSERT INTO dbo.Contract with(rowlock)(id_counteragent,idTypeAgreement,contract_date,contract_number,contract_subject)" +
                    " VALUES (?, ?, ?, ?, ?) ";

    private static final String UPDATE_CONTRACT =
            "UPDATE Contract SET idTypeAgreement=?,contract_date=?,contract_number=?,contract_subject=? " +
                    " WHERE id_contract =? ";

    private static final String DELETE_CONTRACT_BY_ID = "DELETE FROM Contract WHERE id_contract=?";


    private JdbcTemplate jdbcTemplate;

    public String newDbContractId;

    @Autowired
    public DbContractRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Collection<DbContract> findAll() {
        return jdbcTemplate.query(SELECT_ALL_CONTRACTS, new DbContractExtractor());
    }

    public Optional<DbContract> findByName(String name) throws SQLException {
        final Collection<DbContract> contracts = jdbcTemplate.query(
                SELECT_CONTRACT_BY_NAME_JOIN_QUERY,
                new Object[]{name}, new int[]{Types.VARCHAR},
                new DbContractExtractor()
        );
        if (Objects.isNull(contracts) || contracts.isEmpty()) {
            return Optional.empty();
        } else if (contracts.size() > 1) {
            throw new SQLException(
                    String.format("Incorrect query result. For contract_name=[%s] was found [%s] contracts", name, contracts.size())
            );
        } else {
            return Optional.ofNullable(contracts.iterator().next());
        }
    }

    @Override
    public Optional<DbContract> findById(String id) {
        final Collection<DbContract> contracts = jdbcTemplate.query(
                SELECT_CONTRACT_BY_ID_QUERY,
                new Object[]{id}, new int[]{Types.BIGINT},
                new DbContractExtractor()
        );
        return Objects.isNull(contracts) || contracts.isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(contracts.iterator().next());
    }

    @Override
    public void create(DbContract contract) {
    /*    final KeyHolder keyHolder = new GeneratedKeyHolder();
        final int affectedRow = jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            INSERT_CONTRACT,
                            new String[]{"id"}
                    );
                    ps.setString(1, contract.getContractName());
                    ps.setString(2, contract.getGroupName());
                    ps.setString(3, contract.getDirectorFirstName());
                    ps.setString(4, contract.getDirectorSurname());
                    ps.setString(5, contract.getDirectorPatronymic());
                    ps.setString(6, contract.getPhoneNumber());
                    ps.setString(7, contract.getMail());
                    ps.setString(8, contract.getAddress());

                    return ps;
                },
                keyHolder
        );
        final String newDbContractId = Objects.requireNonNull(
                keyHolder.getKey(),
                "New created DbContract [id] must not be null"
        ).toString();

        return newDbContractId;*/
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
            System.out.println(contract);
            PreparedStatement statement = connection.prepareStatement(INSERT_CONTRACT);
            statement.setObject(1, UUID.fromString(contract.getCounteragentId()));
            statement.setInt(2, contract.getContractType());
            statement.setString(3, contract.getContractDate());
            statement.setString(4, contract.getContractNumber());
            statement.setString(5, contract.getContractSubject());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(DbContract contract) throws DataAccessException {
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

            PreparedStatement statement = connection.prepareStatement(UPDATE_CONTRACT);
            statement.setInt(1, contract.getContractType());
            statement.setString(2, contract.getContractDate());
            statement.setString(3, contract.getContractNumber());
            statement.setString(4, contract.getContractSubject());
            statement.setObject(5, UUID.fromString(contract.getId()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String contractId) {
        jdbcTemplate.update(DELETE_CONTRACT_BY_ID, contractId);
    }
}
