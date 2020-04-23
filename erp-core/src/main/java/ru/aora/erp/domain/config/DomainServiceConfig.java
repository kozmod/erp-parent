package ru.aora.erp.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.aora.erp.aspect.ServiceLoggingAspect;
import ru.aora.erp.security.map.DashboardAuthorityUrlMap;
import ru.aora.erp.domain.UserGateway;
import ru.aora.erp.domain.service.ContractService;
import ru.aora.erp.domain.service.CounteragentService;
import ru.aora.erp.domain.service.KsService;
import ru.aora.erp.domain.service.user.UserAuthorityCacheService;
import ru.aora.erp.domain.service.user.UserService;
import ru.aora.erp.repository.gateway.DbContractGateway;
import ru.aora.erp.repository.gateway.DbCounteragentGateway;
import ru.aora.erp.repository.gateway.DbKsGateway;

import java.util.List;

@Configuration
@ComponentScan("ru.aora.erp.domain.service") //todo remove in the future
@EnableAspectJAutoProxy
public class DomainServiceConfig {

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ServiceLoggingAspect serviceLoggingAspect() {
        return new ServiceLoggingAspect();
    }

    @Bean
    public UserAuthorityCacheService userAuthorityCacheService(DashboardAuthorityUrlMap map){
        return new UserAuthorityCacheService(
                List.of(map.getAuthoritiesUrls())
        );
    }

    @Bean
    public UserService userService(
            UserGateway gateway,
            PasswordEncoder passwordEncoder,
            UserAuthorityCacheService userAuthorityCacheService
    ){
        return new UserService(gateway,passwordEncoder,userAuthorityCacheService);
    }

    @Bean
    public KsService ksService(DbKsGateway gateway){
        return new KsService(gateway);
    }

    @Bean
    public ContractService contractService(DbContractGateway gateway){
        return new ContractService(gateway);
    }

    @Bean
    public CounteragentService counteragentService(DbCounteragentGateway gateway){
        return new CounteragentService(gateway);
    }

//    @Bean //todo: используем или нет???
//    public CommonsRequestLoggingFilter logFilter() {
//        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
//        filter.setIncludeQueryString(true);
//        filter.setIncludeQueryString(true);
//        filter.setIncludePayload(true);
//        filter.setMaxPayloadLength(10000);
//        filter.setIncludeHeaders(false);
//        filter.setAfterMessagePrefix("RQ Data : ");
//        return filter;
//    }
}
