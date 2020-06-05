package repository.user.it;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.aora.erp.aspect.AspectConfig;
import ru.aora.erp.domain.config.UserServiceConfig;
import ru.aora.erp.repository.config.RepositoryConfig;
import ru.aora.erp.domain.service.user.UserService;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.model.entity.business.UserAuthority;

import java.util.Collections;


@Ignore("Use only check real DB")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, UserServiceConfig.class, AspectConfig.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void should_getUser() {
        User user = userService.loadUserByUsername("u");
        System.out.println(passwordEncoder.matches( "u",user.getPassword()));
        System.out.println(user.getPassword());
        user.getAuthorities().forEach(
                a -> {
                    System.out.println(a.getModuleName());
                    System.out.println(a.getRoleName());
                }
        );
        System.out.println(user);
    }

    @Test
    public void should_saveUser() {
        User user = new User();
        user.setUsername("u");
        user.setFirstName("Test");
        user.setSurname("S-Test");
        user.setPatronymic("P-test");
        user.setPhoneNumber("123456");

        user.setPassword("u");
        user.setAuthorities(Collections.singletonList(new UserAuthority("CORE","TEST")));
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);

        User res = userService.create(user);
//        MsgServiceResult res = userService.update(user);

        System.out.println(res);
//        System.out.println(res.getAuthorities());
    }
}
