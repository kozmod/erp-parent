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

import java.util.*;

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
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder); //todo remove wen work DB
        auth.inMemoryAuthentication()
                .withUser("a")
                .password(passwordEncoder.encode("a"))
                .roles(DashboardAuthorityUrlMap.ADMIN.getAuthority())
                .and()
                .withUser("u")
                .password(passwordEncoder.encode("u"))
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
//        urlRegistry.antMatchers(DashboardUrl.INCLUDE_ROOT_MAPPING).permitAll();
        defineAuthoritiesMapping(urlRegistry, authorityCache.urlAuthorityMap()); //todo enable user security
        urlRegistry.anyRequest().authenticated(); //todo enable user security

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

    private void defineAuthoritiesMapping(
            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry,
            Map<? extends GrantedAuthority, Set<String>> authoritySetMap
    ) {
        Map<String, Set<String>> urlAuthorities = toUrlAuthorities(authoritySetMap);
        for (var entry : Objects.requireNonNull(urlAuthorities.entrySet())) {
            var url = entry.getKey();
            var authorities = entry.getValue().toArray(new String[0]);
            registry.antMatchers(url)
                    .hasAnyRole(authorities);
        }
    }

    private static Map<String, Set<String>> toUrlAuthorities(Map<? extends GrantedAuthority, Set<String>> authorityUrls) {
        Map<String, Set<String>> urlAuthorities = new HashMap<>();
        for (var entry : authorityUrls.entrySet()) {
            for (var url : entry.getValue()) {
                var authorities = urlAuthorities.computeIfAbsent(url, k -> new HashSet<>());
                authorities.add(entry.getKey().getAuthority());
                urlAuthorities.put(url, authorities);
            }
        }
        return urlAuthorities;
    }
}