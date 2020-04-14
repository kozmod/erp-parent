package ru.aora.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.aora.erp.config.ControllerConfig;
import ru.aora.erp.config.authority.SecurityConfig;
import ru.aora.erp.config.DomainServiceConfig;
import ru.aora.erp.config.RepositoryConfig;

@SpringBootApplication(scanBasePackages = {
        "ru.aora.erp.aspect"
})
@Import({
        SecurityConfig.class,
        ControllerConfig.class,
        DomainServiceConfig.class,
        RepositoryConfig.class
})
public class ErpApp {

    public static void main(String[] args) {
        SpringApplication.run(ErpApp.class, args);
    }
}
