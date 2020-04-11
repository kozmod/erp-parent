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
        System.out.println(passwordEncoder.encode("123456"));
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
//    @Test
//    public void should_saveUser() {
//        DbSubAuthority adminSubA
//                = createDbSubAuthorityIfNotFound("ADMIN");
//        DbSubAuthority userSubA
//                = createDbSubAuthorityIfNotFound("USER");
//
//        createAuthorityIfNotFound("CORE", Arrays.asList(adminSubA));
//        createAuthorityIfNotFound("GARANT", Arrays.asList(userSubA));
//
//        DbAuthority adminRole = roleRepository.findByName("CORE").get();
//        DbUser user = new DbUser();
//        user.setUsername("U2");
//        user.setFirstName("Test");
//        user.setSurname("S-Test");
//        user.setPatronymic("P-test");
//        user.setPhoneNumber("123456");
//
//        user.setPassword("test");
//        user.setAuthorities(Collections.singletonList(adminRole));
//        user.setEnabled(true);
//        userRepository.save(user);
//    }
//
//    private DbSubAuthority createDbSubAuthorityIfNotFound(String name) {
//        Optional<DbSubAuthority> subAuthority = privilegeRepository.findByName(name);
//        if (subAuthority.isEmpty()) {
//            DbSubAuthority newSub = new DbSubAuthority();
//            newSub.setName(name);
//            return privilegeRepository.save(newSub);
//        }
//        return subAuthority.get();
//    }
//
//    private DbAuthority createAuthorityIfNotFound(String name, Collection<DbSubAuthority> privileges) {
//        Optional<DbAuthority> authority = roleRepository.findByName(name);
//        if (authority.isEmpty()) {
//            DbAuthority newA = new DbAuthority();
//            newA.setName(name);
//            newA.setSubAuthorities(privileges);
//            return roleRepository.save(newA);
//        }
//        return authority.get();
//    }
}
