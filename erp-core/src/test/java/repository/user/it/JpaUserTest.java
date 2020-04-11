package repository.user.it;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.aora.erp.config.RepositoryConfig;
import ru.aora.erp.model.entity.db.user.DbAuthority;
import ru.aora.erp.model.entity.db.user.DbSubAuthority;
import ru.aora.erp.model.entity.db.user.DbUser;
import ru.aora.erp.repository.jpa.*;

import java.util.*;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class})
public class JpaUserTest {

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private JpaAuthorityRepository roleRepository;

    @Autowired
    private JpaSubAuthorityRepository privilegeRepository;

    //    @Transactional
    @Test
    public void should_getUser() {
        Optional<DbUser> user = userRepository.findByName("U2");
        user.ifPresent(
                u -> u.getAuthorities().forEach(
                        a -> {
                            System.out.println(a.getName());
                            a.getSubAuthorities().forEach(s -> System.out.println(s.getName()));
                        }
                )
        );
        System.out.println(user.get());
    }

    //    @Transactional
    @Test
    public void should_saveUser() {
        DbSubAuthority adminSubA
                = createDbSubAuthorityIfNotFound("ADMIN");
        DbSubAuthority userSubA
                = createDbSubAuthorityIfNotFound("USER");

        createAuthorityIfNotFound("CORE", Arrays.asList(adminSubA));
        createAuthorityIfNotFound("GARANT", Arrays.asList(userSubA));

        DbAuthority adminRole = roleRepository.findByName("CORE").get();
        DbUser user = new DbUser();
        user.setUsername("U2");
        user.setFirstName("Test");
        user.setSurname("S-Test");
        user.setPatronymic("P-test");
        user.setPhoneNumber("123456");

        user.setPassword("test");
        user.setAuthorities(Collections.singletonList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);
    }

    private DbSubAuthority createDbSubAuthorityIfNotFound(String name) {
        Optional<DbSubAuthority> subAuthority = privilegeRepository.findByName(name);
        if (subAuthority.isEmpty()) {
            DbSubAuthority newSub = new DbSubAuthority();
            newSub.setName(name);
            return privilegeRepository.save(newSub);
        }
        return subAuthority.get();
    }

    private DbAuthority createAuthorityIfNotFound(String name, Collection<DbSubAuthority> privileges) {
        Optional<DbAuthority> authority = roleRepository.findByName(name);
        if (authority.isEmpty()) {
            DbAuthority newA = new DbAuthority();
            newA.setName(name);
            newA.setSubAuthorities(privileges);
            return roleRepository.save(newA);
        }
        return authority.get();
    }
}
