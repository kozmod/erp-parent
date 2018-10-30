import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.aora.erp.config.UserDataBaseConfig;
import ru.aora.erp.repository.UserRepository;


import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UserDataBaseConfig.class})
//@TransactionConfiguration
public class JpaTest {

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private UserTestRepository userTestRepository;


//    @Autowired
//    private ProductRepository productRepository;

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUser_thenCreated() {
        assertNotNull(userRepository.findByName("z"));
        System.out.println(userRepository.findByName("z"));
    }

//    @Test
//    @Transactional("userTransactionManager")
//    public void shouldFindUserTest() {
//        assertNotNull(userTestRepository.findByName("z"));
//        System.out.println(userTestRepository.findByName("z"));
//    }

//    @Test
//    @Transactional("userTransactionManager")
//    public void whenCreatingUsersWithSameEmail_thenRollback() {
//        User user1 = new User();
//        user1.setName("John");
//        user1.setEmail("john@test.com");
//        user1.setAge(20);
//        user1 = userRepository.save(user1);
//        assertNotNull(userRepository.findOne(user1.getId()));
//
//        User user2 = new User();
//        user2.setName("Tom");
//        user2.setEmail("john@test.com");
//        user2.setAge(10);
//        try {
//            user2 = userRepository.save(user2);
//        } catch (DataIntegrityViolationException e) {
//        }
//
//        assertNull(userRepository.findOne(user2.getId()));
//    }
//
//    @Test
//    @Transactional("productTransactionManager")
//    public void whenCreatingProduct_thenCreated() {
//        Product product = new Product();
//        product.setName("Book");
//        product.setId(2);
//        product.setPrice(20);
//        product = productRepository.save(product);
//
//        assertNotNull(productRepository.findOne(product.getId()));
//    }
}