package ru.aora.erp.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.aora.erp.domain.UserGateway;
import ru.aora.erp.domain.service.user.UserAuthorityCacheService;
import ru.aora.erp.domain.service.user.UserService;
import ru.aora.erp.repository.gateway.DbModuleRolePairGateway;
import ru.aora.erp.repository.gateway.DbUserGateway;
import ru.aora.erp.repository.jpa.JpaModuleRepository;
import ru.aora.erp.repository.jpa.JpaModuleRolePairRepository;
import ru.aora.erp.repository.jpa.JpaRoleRepository;
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
    public DbModuleRolePairGateway dbModuleRolePairGateway(
            JpaModuleRepository moduleRepository,
            JpaRoleRepository roleRepository,
            JpaModuleRolePairRepository moduleRolePairRepository
    ) {
        return new DbModuleRolePairGateway(moduleRepository, roleRepository, moduleRolePairRepository);
    }

    @Bean
    public DbUserGateway dbUserGateway(
            JpaUserRepository userRepository,
            DbModuleRolePairGateway dbModuleRolePairGateway
    ) {
        return new DbUserGateway(userRepository, dbModuleRolePairGateway);
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
