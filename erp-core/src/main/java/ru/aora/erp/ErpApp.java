package ru.aora.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.aora.erp.presentation.config.PresentationConfig;
import ru.aora.erp.security.SecurityConfig;
import ru.aora.erp.domain.config.DomainServiceConfig;
import ru.aora.erp.repository.config.RepositoryConfig;

@SpringBootApplication(scanBasePackages = {
        "ru.aora.erp.aspect"
})
@Import({
        SecurityConfig.class,
        DomainServiceConfig.class,
        PresentationConfig.class,
        RepositoryConfig.class
})
public class ErpApp {

    public static void main(String[] args) {
        SpringApplication.run(ErpApp.class, args);
    }
}
