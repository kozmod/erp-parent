import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.aora.erp.component.CoreModuleAuthority;
import ru.aora.erp.config.UserDataBaseConfig;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.model.entity.db.DbModuleRule;
import ru.aora.erp.model.entity.db.DbUser;
import ru.aora.erp.model.entity.mapper.UserMapper;
import ru.aora.erp.repository.crud.user.DbUserRepository;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        UserDataBaseConfig.class})
//@TransactionConfiguration
public class JpaTest {

    @Autowired
    private DbUserRepository userRepository;

    @Test
    @Transactional("userTransactionManager")
    public void whenFindByName() throws SQLException {
        var dbUser = userRepository.findByName("z").get();
        System.out.println(
                "\n_______________________________________\n" +
                        dbUser +
                        "\n_______________________________________\n"
        );
        assertNotNull(userRepository.findByName("z"));
    }

    @Test
    @Transactional("userTransactionManager")
    public void shouldConvertDbUser() throws SQLException {
        var dbUser = userRepository.findByName("z").get();

        var userMapper = UserMapper.INSTANCE;
        var user = userMapper.toUser(
                dbUser,
                Arrays.asList(CoreModuleAuthority.values())
        );

        System.out.println(
                "\n_______________________________________\n" +
                        dbUser +
                        "\n_______________________________________\n"
        );
        assertNotNull(user);
        System.out.println(
                "\n_______________________________________\n" +
                        user +
                        "\n_______________________________________\n"
        );
    }


    @Test
    @Ignore
//    @Transactional("userTransactionManager")
    public void shouldSaveDbUser() {
        var dbUser = newDbUser();
        userRepository.create(dbUser);
        System.out.println(
                "\n_______________________________________\n" +
                        dbUser +
                        "\n_______________________________________\n"
        );
    }


    private DbUser newDbUser() {
        return new DbUser()
                .setUsername("z")
                .setPassword(new BCryptPasswordEncoder().encode("y"))
                .setFirstName("Иванов")
                .setSurname("Иван")
                .setPatronymic("Иванович")
                .setAccountNonExpired(true)
                .setAccountNonLocked(true)
                .setCredentialsNonExpired(true)
                .setEnabled(true)
                .setMail("y-mail@gMail.com")
                .setEmployeePosition("Слесарь")
                .setPhoneNumber("+7(926)1057452")
                .setAuthorities(
                        Set.of(
                                new DbModule()
                                        .setId(1L)
                                        .setName("CoreModuleAuthority")
                                        .setModuleRoles(
                                                Set.of(
                                                        new DbModuleRule()
                                                                .setId(1)
                                                                .setName("GET_USERS")
                                                )
                                        )
                        )
                );
    }
}