package ru.aora.erp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.aora.erp.model.user.UserRole;
import ru.aora.erp.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String INCLUDE_ROOT_MAPPING = "/**";
    public static final String LOGOUT_MAPPING = "/logout";
    public static final String LOGIN_MAPPING = "/login";
    public static final String ROOT_MAPPING = "/";

    private static final String ALL_CSS_MAPPING = "/css/**";
    private static final String ALL_JS_MAPPING = "/js/**";
    private static final String ALL_ICONS_MAPPING = "/icons/**";

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
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
//                .antMatchers(USERS_MANAGE_MAPPING).hasAnyAuthority(UserRole.ADMIN.getAuthority())
                .antMatchers(INCLUDE_ROOT_MAPPING).hasAnyAuthority(UserRole.USER.getAuthority(),UserRole.ADMIN.getAuthority())
//                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage(LOGIN_MAPPING)
                .defaultSuccessUrl(ROOT_MAPPING)
                .permitAll()

                .and()
                .logout()
                .logoutRequestMatcher(
                        new AntPathRequestMatcher(LOGOUT_MAPPING)
                )
                .logoutSuccessUrl(LOGIN_MAPPING);
    }


}