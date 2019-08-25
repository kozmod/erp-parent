package ru.aora.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.aora.erp.config.SecurityConfig;
import ru.aora.erp.config.ServiceConfig;
import ru.aora.erp.config.RepositoryConfig;

@SpringBootApplication(scanBasePackages = {
        "ru.aora.erp.controller",
        "ru.aora.erp.component"
})
@Import({
        SecurityConfig.class,
        ServiceConfig.class,
        RepositoryConfig.class
})
public class ErpApp {

    public static void main(String[] args) {
        SpringApplication.run(ErpApp.class, args);
    }
}
