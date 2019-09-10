package repository.user.it;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.aora.erp.config.RepositoryConfig;
import ru.aora.erp.model.entity.db.DbUser;
import ru.aora.erp.repository.crud.CrudRepository;

import java.util.Optional;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static repository.user.it.DbUserUtils.newDbUserWithOutAuthorities;
import static repository.user.it.TestUtils.newRandomString;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class})
public class DbUserRepositoryTest {

    @Autowired
    private CrudRepository<DbUser,Long> userRepository;

    @Test
    public void shouldFindById() {
        final Optional<DbUser> user = userRepository.findById(1L);
        assertTrue(user.isPresent());
    }

    @Test
    public void shouldCreateUserWithoutAuthorities() {
        final DbUser user = newDbUserWithOutAuthorities(newRandomString(7));
        final DbUser newUserId = userRepository.create(user);
        assertNotEquals(newUserId, 0L);
        //delete
//        userRepository.delete(newUserId);
    }

//    @Test
//    public void shouldFindUserByName() throws SQLException {
//        final String USER_NAME = "x_TEST_USER_x";
//        //create
//        userRepository.create(newDbUserWithOutAuthorities(USER_NAME));
//
//        final Optional<DbUser> user = userRepository.findByName(USER_NAME);
//        assertTrue(user.isPresent());
//        assertNotNull(user.get());
//        user.ifPresent(dbUser ->{
//            assertNotEquals(dbUser.getId(), 0L);
//            assertEquals(dbUser.getUsername(), USER_NAME);
//            //delete
//            userRepository.delete(dbUser.getId());
//        });
//    }
//
//    @Test
//    public void shouldDeleteUser() throws SQLException {
//        //arrange
//        final DbUser userToDelete = newDbUserWithOutAuthorities(newRandomString(7));
//        final long userToDeleteId = userRepository.create(userToDelete);
//        assertNotEquals(userToDeleteId, 0L);
//
//        //act
//        userRepository.delete(userToDeleteId);
//
//        //assert
//        final Optional<DbUser> user = ((DbUserRepository)userRepository).findByName(userToDelete.getUsername());
//        assertTrue(user.isEmpty());
//    }
}
