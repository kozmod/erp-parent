package ru.aora.erp.security;

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
import ru.aora.erp.security.map.DashboardAuthorityUrlMap;
import ru.aora.erp.presentation.controller.dashboard.DashboardUrl;

import java.util.Map;
import java.util.Objects;
import java.util.Set;


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
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
        auth.inMemoryAuthentication()
                .withUser("a")
                .password(passwordEncoder.encode("123456"))
                .roles(DashboardAuthorityUrlMap.ADMIN.getAuthority())
                .and()
                .withUser("u")
                .password(passwordEncoder.encode("123456"))
                .roles(DashboardAuthorityUrlMap.USER.getAuthority());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        var urlRegistry = http.authorizeRequests()
                .antMatchers(ALL_CSS_MAPPING).permitAll()
                .antMatchers(ALL_JS_MAPPING).permitAll()
                .antMatchers(ALL_ICONS_MAPPING).permitAll()
                .antMatchers(DashboardUrl.LOGIN_MAPPING).permitAll()
                .antMatchers(DashboardUrl.LOGOUT_MAPPING).permitAll();
//        defineAuthoritiesMapping(urlRegistry, authorityCache.urlAuthorityMap()); //todo enable user security
        urlRegistry.anyRequest().authenticated();

        http.formLogin()
                .loginPage(DashboardUrl.LOGIN_MAPPING)
                .defaultSuccessUrl(DashboardUrl.MAPPING)
                .successForwardUrl(DashboardUrl.MAPPING)
                .permitAll();
        http.logout()
                .clearAuthentication(true)
                .logoutUrl(DashboardUrl.LOGOUT_MAPPING)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher(DashboardUrl.LOGOUT_MAPPING))
                .logoutSuccessUrl(DashboardUrl.LOGIN_MAPPING)
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
                registry.antMatchers(url).hasRole(authorityVal);
            }
        }
        return registry;
    }
}