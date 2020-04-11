package ru.aora.erp.presentation.entity.dto.user;

import org.apache.commons.collections.CollectionUtils;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.model.entity.business.UserAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public final class UserIdModuleAuthorityDto {
    private String userId;
    private Collection<ModuleAuthorityDto> moduleAuthorityDtoList;

    public UserIdModuleAuthorityDto(String userId, Collection<ModuleAuthorityDto> moduleAuthorityDtoList) {
        this.userId = userId;
        this.moduleAuthorityDtoList = moduleAuthorityDtoList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Collection<ModuleAuthorityDto> getModuleAuthorityDtoList() {
        return moduleAuthorityDtoList;
    }

    public void setModuleAuthorityDtoList(Collection<ModuleAuthorityDto> moduleAuthorityDtoList) {
        this.moduleAuthorityDtoList = moduleAuthorityDtoList;
    }

    public static Collection<UserIdModuleAuthorityDto> of(Collection<User> users, Collection<UserAuthority> allAuthorities) {
        final Collection<UserIdModuleAuthorityDto> collection = new ArrayList<>(users.size());
        for (User user : users) {
            final Map<String, ModuleAuthorityDto> map = toModuleNameAuthorityMap(user.getAuthorities(), allAuthorities);
            collection.add(new UserIdModuleAuthorityDto(user.getId(), map.values()));
        }
        return collection;
    }

    @SuppressWarnings("RedundantIfStatement")
    private static Map<String, ModuleAuthorityDto> toModuleNameAuthorityMap(Collection<UserAuthority> userAuthorities, Collection<UserAuthority> allAuthorities) {
        final Map<String, ModuleAuthorityDto> map = new LinkedHashMap<>(allAuthorities.size(), 1.1f);
        for (var allAuthority : allAuthorities) {
            final var moduleName = allAuthority.getRootAuthority();
            var moduleAuthorityDto = map.get(moduleName);
            if (moduleAuthorityDto == null) {
                map.put(
                        moduleName,
                        moduleAuthorityDto = new ModuleAuthorityDto(moduleName, new HashMap<>())
                );
            }
            if (CollectionUtils.isNotEmpty(userAuthorities) && userAuthorities.contains(allAuthority)) {
                moduleAuthorityDto.moduleMap.put(allAuthority.getSubAuthority(), true);
            } else {
                moduleAuthorityDto.moduleMap.put(allAuthority.getSubAuthority(), false);
            }
        }
        return map;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserIdModuleAuthorityDto.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("moduleAuthorityDtoList=" + moduleAuthorityDtoList)
                .toString();
    }
}
