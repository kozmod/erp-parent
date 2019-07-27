package ru.aora.erp.model.entity.mapper;

import ru.aora.erp.model.entity.business.IdAuthority;

import java.util.List;
import java.util.Optional;

public class ModuleConverter {

    private final List<IdAuthority> allAuthorities;

    public ModuleConverter(List<IdAuthority> allAuthorities) {
        this.allAuthorities = allAuthorities;
    }

//    public DbModule toDbCounteragent(IdAuthority authority){
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
//    public IdAuthority toDbCounteragent(DbModule dbModule){
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
