package ru.aora.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.aora.erp.aspect.ServiceLoggingAspect;
import ru.aora.erp.config.authority.CoreAuthorityConfigMap;
import ru.aora.erp.domain.KsGateway;
import ru.aora.erp.domain.UserGateway;
import ru.aora.erp.domain.service.KsService;
import ru.aora.erp.domain.service.user.UserAuthorityCacheService;
import ru.aora.erp.domain.service.user.UserService;

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
    public UserAuthorityCacheService userAuthorityCacheService(){
        return new UserAuthorityCacheService(
                List.of(CoreAuthorityConfigMap.urlAuthorityMap)
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
    public KsService ksService(KsGateway gateway){
        return new KsService(gateway);
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
