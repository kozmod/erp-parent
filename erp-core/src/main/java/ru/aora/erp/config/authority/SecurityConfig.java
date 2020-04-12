package ru.aora.erp.config.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.aora.erp.domain.service.user.UserAuthorityCacheService;
import ru.aora.erp.domain.service.user.UserService;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static ru.aora.erp.presentation.controller.DashboardController.DASHBOARD_MAPPING;
import static ru.aora.erp.presentation.controller.security.SecurityController.INCLUDE_ROOT_MAPPING;
import static ru.aora.erp.presentation.controller.security.SecurityController.LOGIN_MAPPING;
import static ru.aora.erp.presentation.controller.security.SecurityController.LOGOUT_MAPPING;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ALL_JS_MAPPING = "/js/**";
    private static final String ALL_CSS_MAPPING = "/css/**";
    private static final String ALL_ICONS_MAPPING = "/icons/**";

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthorityCacheService authorityCache;

    @Autowired
    public SecurityConfig(
            UserService userService,
            PasswordEncoder passwordEncoder,
            UserAuthorityCacheService authorityCache
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authorityCache = authorityCache;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        defineAuthoritiesMapping(http.authorizeRequests(), authorityCache.urlAuthorityMap())
        http.authorizeRequests()
                .antMatchers(ALL_CSS_MAPPING).permitAll()
                .antMatchers(ALL_JS_MAPPING).permitAll()
                .antMatchers(ALL_ICONS_MAPPING).permitAll()
                .antMatchers(INCLUDE_ROOT_MAPPING).hasAnyAuthority(CoreAuthorityConfigMap.ADMIN.getAuthority())
//                .antMatchers(INCLUDE_ROOT_MAPPING).hasAnyAuthority(CoreAuthorityConfigMap.ADMIN.toString())
//                .antMatchers(INCLUDE_ROOT_MAPPING).permitAll() //todo
//                .antMatchers(INCLUDE_ROOT_MAPPING).hasAnyAuthority(ADMIN.getAuthority())
//                .antMatchers(DASHBOARD_MAPPING).hasAnyAuthority(UserRole.USER.getAuthority(),UserRole.ADMIN.getAuthority())
//                .antMatchers(INCLUDE_ROOT_MAPPING).authenticated()
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage(LOGIN_MAPPING)
                .defaultSuccessUrl(DASHBOARD_MAPPING)
                .successForwardUrl(DASHBOARD_MAPPING)
                .permitAll();
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_MAPPING))
                .logoutSuccessUrl(LOGIN_MAPPING) //todo not exists: /@%7B/logout%7D - what ????
                .permitAll();
    }

    private ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry defineAuthoritiesMapping(
            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry,
            Map<? extends GrantedAuthority, Set<String>> authoritySetMap
    ) {
        for (var entry : Objects.requireNonNull(authoritySetMap.entrySet())) {
            final var authority = entry.getKey();
            final var authorityVal = authority.getAuthority();
            for (var url : entry.getValue()) {
                registry.antMatchers(url).hasAnyAuthority(authorityVal);
            }
        }
        return registry;
    }
}