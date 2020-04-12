package repository.user.it;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.aora.erp.config.RepositoryConfig;
import ru.aora.erp.config.UserServiceConfig;
import ru.aora.erp.domain.service.user.UserService;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.model.entity.business.UserAuthority;

import java.util.Collections;


@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, UserServiceConfig.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //    @Transactional
    @Test
    public void should_getUser() {
        User user = userService.loadUserByUsername("User");
        System.out.println(passwordEncoder.matches(user.getPassword(), "123456"));
        System.out.println(user.getPassword());
        user.getAuthorities().forEach(
                a -> {
                    System.out.println(a.getRootAuthority());
                    System.out.println(a.getSubAuthority());
                }
        );
        System.out.println(user);
    }

    //    @Transactional
    @Test
    public void should_saveUser() {
        User user = new User();
        user.setUsername("U1");
        user.setFirstName("Test");
        user.setSurname("S-Test");
        user.setPatronymic("P-test");
        user.setPhoneNumber("123456");

        user.setPassword("test");
        user.setAuthorities(Collections.singletonList(new UserAuthority("CORE","ADMIN")));
        user.setEnabled(true);

        User res = userService.createUser(user);

        System.out.println(res);
        System.out.println(res.getAuthorities());
    }
}
