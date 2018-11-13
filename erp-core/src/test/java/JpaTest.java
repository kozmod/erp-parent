import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.aora.erp.component.CoreModuleAuthority;
import ru.aora.erp.config.CoreConfig;
import ru.aora.erp.config.SecurityConfig;
import ru.aora.erp.config.UserDataBaseConfig;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.model.entity.db.DbModuleRule;
import ru.aora.erp.model.entity.db.DbUser;
import ru.aora.erp.model.entity.db.UserConverter;
import ru.aora.erp.repository.DbUserRepository;


import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
//        SecurityConfig.class,
//        CoreConfig.class,
        UserDataBaseConfig.class})
//@ComponentScan({
//        "ru.aora.erp.controller",
//        "ru.aora.erp.service",
//        "ru.aora.erp.component"
//})
//@TransactionConfiguration
public class JpaTest {

    @Autowired
    private DbUserRepository userRepository;


    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUser_thenCreated() {
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
    public void shouldConvertDbUser() {
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
//    @Transactional("userTransactionManager")
    public void shouldSaveDbUser() {
        var dbUser = newDbUser();
        userRepository.save(dbUser);
        System.out.println(
                "\n_______________________________________\n"+
                        dbUser +
                        "\n_______________________________________\n"
        );
    }


    private DbUser newDbUser() {
        var bdUser = DbUser.builder()
                .withUsername("z")
                .withPassword(new BCryptPasswordEncoder().encode("z"))
                .withAccountNonExpired(true)
                .withAccountNonLocked(true)
                .withCredentialsNonExpired(true)
                .withEnabled(true)
                .withMail("z")
                .withAuthorities(
                        Set.of(
                                DbModule.builder()
                                        .withId(2)
                                        .withName("CoreModuleAuthority")
                                        .withModuleRoles(
                                                Set.of(
                                                        DbModuleRule.builder()
                                                                .withId(2002)
                                                                .withName("GET_USERS")
                                                                .build()
                                                )
                                        )
                                        .build()
                        )
                )
                .build();
        return bdUser;
    }

}