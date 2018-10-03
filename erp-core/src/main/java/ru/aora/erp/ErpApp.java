package ru.aora.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.aora.erp.config.SecurityConfig;
import ru.aora.erp.config.SupportBeanConfig;

@SpringBootApplication(scanBasePackages = {
        "ru.aora.erp.controller",
        "ru.aora.erp.service",
        "ru.aora.erp.repository"

})
@Import({
        SecurityConfig.class,
        SupportBeanConfig.class
})
public class ErpApp {

    public static void main(String[] args){
        SpringApplication.run(ErpApp.class, args);
    }
}
