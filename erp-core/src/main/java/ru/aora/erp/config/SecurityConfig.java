package ru.aora.erp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.aora.erp.model.entity.IdAuthority;
import ru.aora.erp.model.identifier.ModuleIdentifier;
import ru.aora.erp.service.AuthorityModulesIdentifiersService;
import ru.aora.erp.service.UserService;

import java.util.Map;

import static ru.aora.erp.component.CoreModuleIdentifier.DASHBOARD_MAPPING;
import static ru.aora.erp.component.CoreModuleIdentifier.LOGIN_MAPPING;
import static ru.aora.erp.component.CoreModuleIdentifier.LOGOUT_MAPPING;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ALL_CSS_MAPPING = "/css/**";
    private static final String ALL_JS_MAPPING = "/js/**";
    private static final String ALL_ICONS_MAPPING = "/icons/**";

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityModulesIdentifiersService authorityModulesIdentifiersService;

    @Autowired
    public SecurityConfig(
            UserService userService,
            PasswordEncoder passwordEncoder,
            AuthorityModulesIdentifiersService authorityModulesIdentifiersService
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authorityModulesIdentifiersService = authorityModulesIdentifiersService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(ALL_CSS_MAPPING).permitAll()
                .antMatchers(ALL_JS_MAPPING).permitAll()
                .antMatchers(ALL_ICONS_MAPPING).permitAll()
//                .antMatchers(INCLUDE_ROOT_MAPPING).hasAnyAuthority(UserRole.USER.getAuthority(),UserRole.ADMIN.getAuthority())
//                .antMatchers(DASHBOARD_MAPPING).hasAnyAuthority(UserRole.USER.getAuthority(),UserRole.ADMIN.getAuthority())
//                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage(LOGIN_MAPPING)
                .defaultSuccessUrl(DASHBOARD_MAPPING)
                .successForwardUrl(DASHBOARD_MAPPING)
                .permitAll()

                .and()
                .logout()
                .logoutRequestMatcher(
                        new AntPathRequestMatcher(LOGOUT_MAPPING)
                )
                .logoutSuccessUrl(LOGIN_MAPPING);
        defineAuthoritiesMapping(http);
    }

    private void defineAuthoritiesMapping(final HttpSecurity http) throws Exception {
        for (ModuleIdentifier moduleIdentifier : authorityModulesIdentifiersService.moduleIdentifiers()) {
            for (Map.Entry<String, IdAuthority> entry : moduleIdentifier.moduleMapping().entrySet()) {
                http.authorizeRequests()
                        .antMatchers(entry.getKey())
                        .hasAnyAuthority(entry.getValue().getAuthority());
            }
        }
    }
}