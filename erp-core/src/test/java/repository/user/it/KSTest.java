package repository.user.it;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.aora.erp.domain.config.DomainServiceConfig;
import ru.aora.erp.domain.service.user.UserService;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.model.entity.business.UserAuthority;
import ru.aora.erp.model.entity.db.DbContract;
import ru.aora.erp.model.entity.db.DbKs;
import ru.aora.erp.repository.config.RepositoryConfig;
import ru.aora.erp.repository.gateway.DbKsGateway;
import ru.aora.erp.repository.jpa.JpaContractRepository;
import ru.aora.erp.repository.jpa.JpaKsRepository;

import java.time.LocalDate;
import java.util.Collections;


@Ignore("Use only check real DB")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class})
public class KSTest {

    @Autowired
    private JpaContractRepository repo;


    //    @Transactional
    @Test
    public void should_getUser() {
        DbContract c = repo.save(
                new DbContract()
                        .setContractNumber("ddddddddddddd")
                        .setContractDate(LocalDate.now())
                        .setCounteragentId("D09668DA-80FF-4B03-B85A-931FC3ACFA06")
                        .setContractType(1)
                        .setContractNumber("1111")
                        .setContractSubject("1111")
//                        .setDeactivated(0)
        );

    }
}
