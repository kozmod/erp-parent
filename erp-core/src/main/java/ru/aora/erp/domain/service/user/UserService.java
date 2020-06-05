package ru.aora.erp.domain.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.aora.erp.domain.UserGateway;
import ru.aora.erp.domain.model.MsgServiceResult;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.model.entity.business.UserAuthority;
import ru.aora.erp.utils.common.CommonUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class UserService implements UserDetailsService {

    private final UserGateway gateway;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthorityCacheService authorityCache;

    public UserService(
            UserGateway gateway,
            PasswordEncoder passwordEncoder,
            UserAuthorityCacheService authorityCache

    ) {
        this.gateway = gateway;
        this.passwordEncoder = passwordEncoder;
        this.authorityCache = authorityCache;
    }

    @Override
    public User loadUserByUsername(String name) {
        CommonUtils.requiredNotBlank(name);
        return gateway.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User was not found: " + name));
    }

    public List<User> loadAll() {
        return gateway.loadAll()
                .stream()
                .map(Objects::requireNonNull)
                .collect(Collectors.toList());
    }

    public User create(User user) {
        return gateway.create(prepare(user)).setPassword(null);
    }

    public MsgServiceResult update(User user) {
        return gateway.update(prepare(user))
                .map(u -> MsgServiceResult.success("User updated"))
                .orElseGet(() -> MsgServiceResult.failed("User to update not found"));
    }

    public MsgServiceResult delete(String name) {
        CommonUtils.requiredNotBlank(name);
        return gateway.delete(name)
                .map(u -> MsgServiceResult.success("User deleted"))
                .orElseGet(() -> MsgServiceResult.failed("User to delete not found"));
    }

    private User prepare(User source) {
        Objects.requireNonNull(source);
        source.setAuthorities(removeIfNotExistsInCache(source.getAuthorities()));
        source.setPassword(passwordEncoder.encode(source.getPassword()));
        return source;
    }

    private Collection<UserAuthority> removeIfNotExistsInCache(Collection<UserAuthority> authorities) {
        if (authorities != null && !authorities.isEmpty()) {
            Collection<UserAuthority> res = new ArrayList<>(authorities.size());
            for (UserAuthority authority : authorities) {
                if (authorityCache.exists(authority)) {
                    res.add(authority);
                }
            }
            return res;
        }
        throw new IllegalArgumentException("User must have at least one authorities");
    }
}
