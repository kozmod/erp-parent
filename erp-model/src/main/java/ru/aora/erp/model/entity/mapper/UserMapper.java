package ru.aora.erp.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;
import ru.aora.erp.model.entity.IdAuthority;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.model.entity.db.DbModuleRule;
import ru.aora.erp.model.entity.db.DbUser;
import ru.aora.erp.model.entity.business.User;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Mapper(
        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    default User toUser(DbUser dbUser, List<IdAuthority> allAuthorities) {
        final var user = toUserWithoutAuthorities(dbUser);
        final List<IdAuthority> authorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty((dbUser.getAuthorities()))) {
            for (var module : dbUser.getAuthorities()) {
                for (var roleName : module.getModuleRoles()) {
                    tryFindAuthorities(module.getName(), roleName.getName(), allAuthorities)
                            .ifPresent(authority -> {
                                authority.setModuleId(module.getId());
                                authority.setRuleId(roleName.getId());
                                authorities.add(authority);
                            });
                }
            }
        }
        user.setAuthorities(authorities);
        return user;
    }

    @Mapping(target = "authorities", ignore = true)
    User toUserWithoutAuthorities(DbUser dbUser);

    private Optional<IdAuthority> tryFindAuthorities(String moduleName, String authorityName, List<IdAuthority> allAuthorities) {
        for (var authority : allAuthorities) {
            if (authority.getClass().getSimpleName().equals(moduleName) && authority.getAuthority().equals(authorityName)) {
                return Optional.of(authority);
            }
        }
        return Optional.empty();
    }

    @Mapping(target = "authorities", ignore = true)
    DbUser toDbUserWithoutModules(User user);

    default DbUser toDbUser(User user) {
        final var dbUser = toDbUserWithoutModules(user);
        final Set<DbModule> modules = new HashSet<>();
        if (!CollectionUtils.isEmpty(user.getAuthorities())) {
            for (IdAuthority authority : user.getAuthorities()) {
                tryFindModule(authority.getClass().getSimpleName(), modules)
                        .ifPresentOrElse(
                                dbModule ->
                                        dbModule.getModuleRoles().add(
                                                new DbModuleRule()
                                                        .setId(authority.getRuleId())
                                                        .setName(authority.getAuthority())
                                        )
                                , () ->
                                        modules.add(
                                                new DbModule()
                                                        .setId(authority.getModuleId())
                                                        .setName(authority.getClass().getSimpleName())
                                                        .setModuleRoles(
                                                                Set.of(
                                                                        new DbModuleRule()
                                                                                .setId(authority.getRuleId())
                                                                                .setName(authority.getAuthority())
                                                                )
                                                        )
                                        )
                        );
            }
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
