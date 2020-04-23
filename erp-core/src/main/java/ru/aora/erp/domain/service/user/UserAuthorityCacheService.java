package ru.aora.erp.domain.service.user;

import ru.aora.erp.model.entity.business.UserAuthority;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class UserAuthorityCacheService {

    private final Map<UserAuthority, Set<String>> authorityUrls;

    public UserAuthorityCacheService(List<Map<UserAuthority, Set<String>>> maps) {
        authorityUrls = asOneMap(maps);
    }

    public Collection<UserAuthority> allAuthorities() {
        return Set.copyOf(authorityUrls.keySet());
    }

    public boolean exists(UserAuthority authority){
        return authorityUrls.containsKey(authority);
    }

    private <K, V> Map<K, V> asOneMap(List<Map<K, V>> sources) {
        Map<K, V> map = new HashMap<>();
        for (Map<K, V> source : sources) {
            map.putAll(source);
        }
        return map;
    }

}
