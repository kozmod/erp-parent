package ru.aora.erp.model.entity.converter;

import ru.aora.erp.model.entity.IdAuthority;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.model.entity.db.DbModuleRule;
import ru.aora.erp.model.entity.db.DbUser;
import ru.aora.erp.model.entity.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.nonNull;

public class ModuleConverter {

    private final List<IdAuthority> allAuthorities;

    public ModuleConverter(List<IdAuthority> allAuthorities) {
        this.allAuthorities = allAuthorities;
    }

//    public DbModule convert(IdAuthority authority){
//        return DbModule.builder()
//                .withId(authority.getModuleId())
//                .withName(authority.getClass().getSimpleName())
//                .withModuleRoles(
//                        Set.of(
//                                DbModuleRule.builder()
//                                        .withId(authority.getRuleId())
//                                        .withName(authority.getAuthority())
//                                        .build()
//                        )
//                ).build();
//    }
//
//    public IdAuthority convert(DbModule dbModule){
//        for (var roleName : dbModule.getModuleRoles()) {
//            tryFindAuthorities(dbModule.getName(), roleName.getName())
//                    .ifPresent(authority -> {
//                        authority.setModuleId(dbModule.getId());
//                        authority.setRuleId(roleName.getId());
//                    });
//        }
//    }




    private Optional<IdAuthority> tryFindAuthorities(String moduleName, String authorityName) {
        for (var authority : allAuthorities) {
            if (authority.getClass().getSimpleName().equals(moduleName) && authority.getAuthority().equals(authorityName)) {
                return Optional.of(authority);
            }
        }
        return Optional.empty();
    }

}
