package ru.aora.erp.domain.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.aora.erp.domain.UserGateway;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.model.entity.business.UserAuthority;
import ru.aora.erp.utils.common.CommonUtils;

import java.util.*;
import java.util.function.Function;
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

    public User createUser(User user) {
        return prepareAndExect(user, gateway::create);
    }

    public User updateUser(User user) {
        return prepareAndExect(
                user,
                u -> gateway.update(u)
                        .orElseThrow(() -> new UsernameNotFoundException("User was not updated: " + user))
        );
    }

//    public User deleteUser(User user) {
//        Objects.requireNonNull(user);
//        return gateway.delete(user).orElse(null); //todo think about
//    }

    private User prepareAndExect(User source, Function<User, User> func) {
        Objects.requireNonNull(source);
        source.setAuthorities(removeIfNotExistsInCache(source.getAuthorities()));
        source.setPassword(passwordEncoder.encode(source.getPassword()));
        User res = func.apply(source);
        res.setPassword(null);
        return res;
    }

    private Collection<UserAuthority> removeIfNotExistsInCache(Collection<UserAuthority> authorities) {
        Objects.requireNonNull(authorities);
        Collection<UserAuthority> res = new ArrayList<>(authorities.size());
        for (UserAuthority authority : authorities) {
            if (authorityCache.exists(authority)) {
                res.add(authority);
            }
        }
        return res;
    }


//    private void tryEncodeUserPassword(User user, DbUser dbUser) {
//        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//        }
//    }
}
