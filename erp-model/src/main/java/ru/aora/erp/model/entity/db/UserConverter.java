package ru.aora.erp.model.entity.db;

import ru.aora.erp.model.entity.IdAuthority;
import ru.aora.erp.model.entity.user.User;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        user.setPhoneNumber(dbUser.getPhoneNumber());
        user.setMail(dbUser.getMail());
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

    public DbUser convert(User user) {
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

        final Set<DbModule> modules = new HashSet<>();
        for (IdAuthority authority : user.getAuthorities()) {
            tryFindModule(authority.getClass().getSimpleName(), modules)
                    .ifPresentOrElse(
                            dbModule ->
                                    dbModule.getModuleRoles().add(
                                            DbModuleRule.builder()
                                                    .withId(authority.getRuleId())
                                                    .withName(authority.getAuthority())
                                                    .build()
                                    )
                            , () ->
                                    modules.add(
                                            DbModule.builder()
                                                    .withId(authority.getModuleId())
                                                    .withName(authority.getClass().getSimpleName())
                                                    .withModuleRoles(
                                                            Set.of(
                                                                    DbModuleRule.builder()
                                                                            .withId(authority.getRuleId())
                                                                            .withName(authority.getAuthority())
                                                                            .build()
                                                            )
                                                    ).build()
                                    )
                    );
        }
        dbUser.setAuthorities(modules);
        return dbUser;
    }

    private Optional<DbModule> tryFindModule(String name, Set<DbModule> modules) {
        for (DbModule module : modules) {
            if (module.getName().equals(name)) {
                return Optional.of(module);
            }
        }
        return Optional.empty();

    }


}
