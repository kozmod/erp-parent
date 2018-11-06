package ru.aora.erp.model.entity.db;

import org.springframework.security.core.GrantedAuthority;
import ru.aora.erp.model.entity.user.User;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class UserConverter {

    private final List<GrantedAuthority> allAuthorities;

    public UserConverter(List<GrantedAuthority> allAuthorities) {
        this.allAuthorities = allAuthorities;
    }

    public User convert(DbUser dbUser) {
        requireNonNull(dbUser, "DbUser should not be null");
        final var user = new User();
        user.setId(dbUser.getId());
        user.setUsername(dbUser.getUsername());
        user.setPassword(dbUser.getPassword());
        user.setEnabled(dbUser.isEnabled());
        user.setDel(dbUser.isDel());
        user.setAccountNonExpired(dbUser.isAccountNonExpired());
        user.setAccountNonLocked(dbUser.isAccountNonLocked());
        user.setCredentialsNonExpired(dbUser.isCredentialsNonExpired());

        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (var module : dbUser.getAuthorities()) {
            for (var roleName : module.getModuleRoles()) {
                Optional<GrantedAuthority> aaa = tryFindAuthorities(module.getName(), roleName);
                tryFindAuthorities(module.getName(), roleName).ifPresent(authorities::add);
            }
        }
        user.setAuthorities(authorities);
        return user;
    }

    private Optional<GrantedAuthority> tryFindAuthorities(String moduleName, String authorityName) {
        for (var authority : allAuthorities) {
            if (authority.getClass().getName().equals(moduleName) && authority.getAuthority().equals(authorityName)) {
                return Optional.of(authority);
            }
        }
        return Optional.empty();
    }

}
