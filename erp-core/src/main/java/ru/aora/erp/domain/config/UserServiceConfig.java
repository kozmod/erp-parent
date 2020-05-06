package ru.aora.erp.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.aora.erp.domain.UserGateway;
import ru.aora.erp.domain.service.user.UserAuthorityCacheService;
import ru.aora.erp.domain.service.user.UserService;
import ru.aora.erp.repository.gateway.DbUserGateway;
import ru.aora.erp.repository.jpa.JpaAuthorityRepository;
import ru.aora.erp.repository.jpa.JpaSubAuthorityRepository;
import ru.aora.erp.repository.jpa.JpaUserRepository;
import ru.aora.erp.security.map.DashboardAuthorityUrlMap;

import java.util.List;

@Configuration
public class UserServiceConfig {

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DashboardAuthorityUrlMap coreAuthorityConfigMap() {
        return new DashboardAuthorityUrlMap();
    }

    @Bean
    public UserAuthorityCacheService userAuthorityCacheService(DashboardAuthorityUrlMap map) {
        return new UserAuthorityCacheService(
                List.of(map.getAuthoritiesUrls())
        );
    }

    @Bean
    public DbUserGateway dbUserGateway(
            JpaUserRepository userRepository,
            JpaAuthorityRepository authorityRepository,
            JpaSubAuthorityRepository subAuthorityRepository
    ) {
        return new DbUserGateway(userRepository, authorityRepository, subAuthorityRepository);
    }

    @Bean
    public UserService userService(
            UserGateway gateway,
            PasswordEncoder passwordEncoder,
            UserAuthorityCacheService userAuthorityCacheService
    ) {
        return new UserService(gateway, passwordEncoder, userAuthorityCacheService);
    }
}
