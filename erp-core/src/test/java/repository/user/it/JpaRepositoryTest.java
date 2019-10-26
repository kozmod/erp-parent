package repository.user.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.aora.erp.config.RepositoryConfig;
import ru.aora.erp.model.entity.db.DbContract;
import ru.aora.erp.model.entity.db.DbCounteragent;
import ru.aora.erp.model.entity.db.DbKs;
import ru.aora.erp.model.entity.db.DbUser;
import ru.aora.erp.repository.jpa.JpaContractRepository;
import ru.aora.erp.repository.jpa.JpaCounteragentRepository;
import ru.aora.erp.repository.jpa.JpaKsRepository;
import ru.aora.erp.repository.jpa.JpaUserRepository;

import java.util.Optional;

//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class})
public class JpaRepositoryTest {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private JpaContractRepository jpaContractRepository;

    @Autowired
    private JpaKsRepository jpaKsRepository;

    @Autowired
    private JpaCounteragentRepository jpaCounteragentRepository;

    @Test
    public void should_getUser() {
        Optional<DbUser> user = jpaUserRepository.findById("17ACF058-09EA-432D-A515-56D94974940E");
        System.out.println(user);
    }

    @Test
    public void should_getContract() {
        Optional<DbContract> user = jpaContractRepository.findById("82162CF1-CF56-4541-9C77-01E7B8EBAA13");
        System.out.println(user);
    }

    @Test
    public void should_getKs() {
        Optional<DbKs> user = jpaKsRepository.findById("6222F4DE-9E26-4672-93D3-6ED4EBEAAD31");
        System.out.println(user);
    }

    @Test
    public void should_getCounteragent() {
        Optional<DbCounteragent> user = jpaCounteragentRepository.findById("606D3A59-1399-485A-A7D5-05E91EB10FB1");
        System.out.println(user);
    }
}
