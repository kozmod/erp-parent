package ru.aora.erp.model.entity.db;

import ru.aora.erp.model.entity.IdAuthority;
import ru.aora.erp.model.entity.user.User;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class UserConverter {

    private final List<IdAuthority> allAuthorities;

    public UserConverter(List<IdAuthority> allAuthorities) {
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

        final List<IdAuthority> authorities = new ArrayList<>();
        for (var module : dbUser.getAuthorities()) {
            for (var roleName : module.getModuleRoles()) {
                tryFindAuthorities(module.getName(), roleName.getName())
                        .ifPresent(authority -> {
                            authority.setModuleId(module.getId());
                            authority.setRuleId(roleName.getId());
                            authorities.add(authority);
                        });
            }
        }
        user.setAuthorities(authorities);
        return user;
    }

    private Optional<IdAuthority> tryFindAuthorities(String moduleName, String authorityName) {
        for (var authority : allAuthorities) {
            if (authority.getClass().getSimpleName().equals(moduleName) && authority.getAuthority().equals(authorityName)) {
                return Optional.of(authority);
            }
        }
        return Optional.empty();
    }

    public DbUser convert(User user){
        requireNonNull(user, "DbUser should not be null");
        final var dbUser = new DbUser();
        dbUser.setId(dbUser.getId());
        dbUser.setUsername(dbUser.getUsername());
        dbUser.setPassword(dbUser.getPassword());
        dbUser.setEnabled(dbUser.isEnabled());
        dbUser.setDel(dbUser.isDel());
        dbUser.setAccountNonExpired(dbUser.isAccountNonExpired());
        dbUser.setAccountNonLocked(dbUser.isAccountNonLocked());
        dbUser.setCredentialsNonExpired(dbUser.isCredentialsNonExpired());

        final List<DbModule> modules = new ArrayList<>();
        final List<DbModuleRule> moduleRules = new ArrayList<>();

        for (IdAuthority authority : user.getAuthorities()) {
            final var module = new DbModule();
            module.setId(authority.getModuleId());
//            module.setModuleRoles();
        }

        return dbUser;
    }


}
