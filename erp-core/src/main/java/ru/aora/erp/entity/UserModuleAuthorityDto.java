package ru.aora.erp.entity;

import org.springframework.data.util.Pair;
import ru.aora.erp.model.entity.IdAuthority;
import ru.aora.erp.model.entity.user.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Inner DTO-class: contains "module name" and "active/inactive user roles" map.
 */
public final class UserModuleAuthorityDto {
    private long userId;
    private Collection<ModuleAuthorityDto> moduleAuthorityDtoList;

    public UserModuleAuthorityDto() {
    }

    public UserModuleAuthorityDto(long userId, Collection<ModuleAuthorityDto> moduleAuthorityDtoList) {
        this.userId = userId;
        this.moduleAuthorityDtoList = moduleAuthorityDtoList;
    }

    public static Collection<UserModuleAuthorityDto> of(Collection<User> users, Collection<IdAuthority> allAuthorities) {
        final Collection<UserModuleAuthorityDto> collection = new ArrayList<>(users.size());
        for (User user : users) {
            collection.add(
                    UserModuleAuthorityDto.of(
                            user.getId(),
                            user.getAuthorities(),
                            allAuthorities
                    )
            );
        }
        return collection;
    }

    public static UserModuleAuthorityDto of(long userId, Collection<IdAuthority> userAuthorities, Collection<IdAuthority> allAuthorities) {
        final Map<String, ModuleAuthorityDto> map = new LinkedHashMap<>(allAuthorities.size(), 1.1f);
        for (IdAuthority allAuthority : allAuthorities) {
            final String moduleName = allAuthority.getName();
            ModuleAuthorityDto moduleAuthorityDto = map.get(moduleName);
            if (moduleAuthorityDto == null)
                map.put(
                        moduleName,
                        (moduleAuthorityDto = new ModuleAuthorityDto(moduleName, new HashMap<>()))
                );
            if (userAuthorities.contains(allAuthority))
                moduleAuthorityDto.moduleMap.put(allAuthority.getRuleName(), true);
            else
                moduleAuthorityDto.moduleMap.put(allAuthority.getRuleName(), false);
        }
        return new UserModuleAuthorityDto(userId, map.values());
    }

    public long getUserId() {
        return userId;
    }

    /*private Pair<List<IdAuthority>,List<IdAuthority>> convert(Map<String, Map<String, Boolean>> modules, List<IdAuthority> allAuthorities) {
        final List<IdAuthority> toDelete = new ArrayList<>();
        final List<IdAuthority> toAdd = new ArrayList<>();
        modules.forEach((moduleName, ruleMap) ->
                allAuthorities.forEach(idAuthority ->
                        ruleMap.forEach((roleName, isSelected) -> {
                            if (idAuthority.getName().equals(moduleName) && idAuthority.getRuleName().equals(roleName)) {
                                if (isSelected) {
                                    toAdd.add(idAuthority);
                                } else {
                                    toDelete.add(idAuthority);
                                }
                            }
                        })
                )
        );
        return new Pair<>(toDelete,toAdd);
    }*/

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Collection<ModuleAuthorityDto> getModuleAuthorityDtoList() {
        return moduleAuthorityDtoList;
    }

    public void setModuleAuthorityDtoList(Collection<ModuleAuthorityDto> moduleAuthorityDtoList) {
        this.moduleAuthorityDtoList = moduleAuthorityDtoList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserModuleAuthorityDto.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("moduleAuthorityDtoList=" + moduleAuthorityDtoList)
                .toString();
    }
}
