package ru.aora.erp.entity.dto;

import ru.aora.erp.model.entity.IdAuthority;
import ru.aora.erp.model.entity.business.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public final class UserIdModuleAuthorityDto {
    private long userId;
    private Collection<ModuleAuthorityDto> moduleAuthorityDtoList;

    public UserIdModuleAuthorityDto(long userId, Collection<ModuleAuthorityDto> moduleAuthorityDtoList) {
        this.userId = userId;
        this.moduleAuthorityDtoList = moduleAuthorityDtoList;
    }

    public static Collection<UserIdModuleAuthorityDto> of(Collection<User> users, Collection<IdAuthority> allAuthorities) {
        final Collection<UserIdModuleAuthorityDto> collection = new ArrayList<>(users.size());
        for (User user : users) {
            collection.add(
                    UserIdModuleAuthorityDto.of(
                            user.getId(),
                            user.getAuthorities(),
                            allAuthorities
                    )
            );
        }
        return collection;
    }

    public static UserIdModuleAuthorityDto of(long userId, Collection<IdAuthority> userAuthorities, Collection<IdAuthority> allAuthorities) {
        final Map<String, ModuleAuthorityDto> map = new LinkedHashMap<>(allAuthorities.size(), 1.1f);
        for (IdAuthority allAuthority : allAuthorities) {
            final String moduleName = allAuthority.getName();
            ModuleAuthorityDto moduleAuthorityDto = map.get(moduleName);
            if (moduleAuthorityDto == null) {
                map.put(
                        moduleName,
                        (moduleAuthorityDto = new ModuleAuthorityDto(moduleName, new HashMap<>()))
                );
            }
            if (userAuthorities.contains(allAuthority)) {
                moduleAuthorityDto.moduleMap.put(allAuthority.getRuleName(), true);
            } else {
                moduleAuthorityDto.moduleMap.put(allAuthority.getRuleName(), false);
            }
        }
        return new UserIdModuleAuthorityDto(userId, map.values());
    }

    public long getUserId() {
        return userId;
    }

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
        return new StringJoiner(", ", UserIdModuleAuthorityDto.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("moduleAuthorityDtoList=" + moduleAuthorityDtoList)
                .toString();
    }
}
