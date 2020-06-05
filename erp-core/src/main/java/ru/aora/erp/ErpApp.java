package ru.aora.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.aora.erp.aspect.AspectConfig;
import ru.aora.erp.domain.config.UserServiceConfig;
import ru.aora.erp.presentation.config.PresentationConfig;
import ru.aora.erp.security.SecurityConfig;
import ru.aora.erp.domain.config.GarantServiceConfig;
import ru.aora.erp.repository.config.RepositoryConfig;

@SpringBootApplication(scanBasePackages = {
        "ru.aora.erp.aspect"
})
@Import({
        SecurityConfig.class,
        GarantServiceConfig.class,
        PresentationConfig.class,
        RepositoryConfig.class,
        UserServiceConfig.class,
        AspectConfig.class,
})
public class ErpApp {

    public static void main(String[] args) {
        SpringApplication.run(ErpApp.class, args);
    }
}
