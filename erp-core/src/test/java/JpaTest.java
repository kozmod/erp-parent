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
import ru.aora.erp.model.entity.converter.UserConverter;
import ru.aora.erp.repository.DbUserRepository;


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

        var userConverter = new UserConverter(Arrays.asList(CoreModuleAuthority.values()));
        var user = userConverter.convert(dbUser);

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
        return DbUser.builder()
                .withUsername("z")
                .withPassword(new BCryptPasswordEncoder().encode("y"))
                .withFirstName("Иванов")
                .withSurname("Иван")
                .withPatronymic("Иванович")
                .withAccountNonExpired(true)
                .withAccountNonLocked(true)
                .withCredentialsNonExpired(true)
                .withEnabled(true)
                .withMail("y-mail@gMail.com")
                .withEmployeePosition("Слесарь")
                .withPhoneNumber("+7(926)1057452")
                .withAuthorities(
                        Set.of(
                                DbModule.builder()
                                        .withId(1)
                                        .withName("CoreModuleAuthority")
                                        .withModuleRoles(
                                                Set.of(
                                                        DbModuleRule.builder()
                                                                .withId(1)
                                                                .withName("GET_USERS")
                                                                .build()
                                                )
                                        )
                                        .build()
                        )
                )
                .build();
    }
}