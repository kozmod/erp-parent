package repository.user.it;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.aora.erp.model.entity.db.user.DbModuleRolePair;
import ru.aora.erp.repository.config.RepositoryConfig;
import ru.aora.erp.model.entity.db.user.DbModule;
import ru.aora.erp.model.entity.db.user.DbRole;
import ru.aora.erp.model.entity.db.user.DbUser;
import ru.aora.erp.repository.jpa.*;

import java.time.LocalDateTime;
import java.util.*;

@Ignore("Use only check real DB")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class})
public class JpaUserTest {

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private JpaModuleRepository moduleRepository;

    @Autowired
    private JpaRoleRepository roleRepository;

    @Autowired
    private JpaModuleRolePairRepository jpaModuleRolePairRepository;

    @Test
    public void should_() {
        List<DbModuleRolePair> pairs = jpaModuleRolePairRepository.findAll();
        pairs.forEach(p -> System.out.println(p));
    }

    @Test
    public void should_s() {
        DbModule coreModule = new DbModule().setName("AM");
        DbRole createRole = new DbRole().setName("AR");
//        DbModuleRolePair pair = new DbModuleRolePair()
//                .setUserId("A1B8FE7E-1C98-4B72-9A9D-F470988C2A44")
//                .setModuleName(coreModule)
//                .setRoleName(createRole);
//        pair = jpaModuleRolePairRepository.save(pair);
//        pairs.forEach(p -> System.out.println(p));
    }

    //    @Transactional
    @Test
    public void should_getUserListByname() {
        List<DbUser> users = userRepository.findByName("U3");
        for (DbUser user : users) {
            user.getAuthorities().forEach(System.out::println);
            System.out.println(user);
        }
    }
//
//    @Test
//    public void should_getActiveUserByName() {
//        Optional<DbUser> user = userRepository.findActiveByName("U2");
//        user.ifPresent(
//                u -> u.getAuthorities().forEach(
//                        a -> {
//                            System.out.println(a.getName());
//                            a.getRoles().forEach(s -> System.out.println(s.getName()));
//                        }
//                )
//        );
//        System.out.println(user.get());
//    }

    //    @Transactional
    @Test
    public void should_saveUser() {
//        DbModule coreModule = new DbModule().setName("CORE");
//        DbRole createRole = new DbRole().setName("CREATE");
//
        DbModule m = createModuleIfNotFound("CORE");
        DbRole r = createRoleIfNotFound("ALL");


        DbUser user = new DbUser();
//        user.setId("A1B8FE7E-1C98-4B72-9A9D-F470988C2A44");
        user.setUsername("a");
        user.setFirstName("Test");
        user.setSurname("S-Test");
        user.setPatronymic("P-test");
        user.setPhoneNumber("123456");

        user.setPassword("a");
        user.setAuthorities(
                Collections.singletonList(
                        new DbModuleRolePair().setModuleId(m.getId()).setRoleId(r.getId()).setDeactivationDate(LocalDateTime.now())
                )
        );
        user.setEnabled(true);
        userRepository.save(user);
    }

    private DbRole createRoleIfNotFound(String name) {
        Optional<DbRole> subAuthority = roleRepository.findByName(name);
        if (subAuthority.isEmpty()) {
            DbRole newSub = new DbRole();
            newSub.setName(name);
            return roleRepository.save(newSub);
        }
        return subAuthority.get();
    }

    private DbModule createModuleIfNotFound(String name) {
        Optional<DbModule> authority = moduleRepository.findByName(name);
        if (authority.isEmpty()) {
            DbModule newA = new DbModule();
            newA.setName(name);
            return moduleRepository.save(newA);
        }
        return authority.get();
    }
}
