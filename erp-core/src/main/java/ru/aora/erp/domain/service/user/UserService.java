package ru.aora.erp.domain.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.aora.erp.domain.UserGateway;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.model.entity.business.UserAuthority;
import ru.aora.erp.utils.common.CommonUtils;

import java.util.*;
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
    public User loadUserByUsername(String name) throws UsernameNotFoundException {
        CommonUtils.requiredNotBlank(name);
        return gateway.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User was not found: " + name));
    }

    public List<User> loadAll() {
        return gateway.findAll()
                .stream()
                .map(Objects::requireNonNull)
                .collect(Collectors.toList());
    }

    public User updateUser(User user) {
        Objects.requireNonNull(user);
        user.setAuthorities(removeIfNotExistsInCache(user.getAuthorities()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var dbUser = gateway.update(user)
                .orElseThrow(() -> new UsernameNotFoundException("User was not updated: " + user));
        dbUser.setPassword(null);
        return dbUser;
    }

    public User deleteUser(User user) {
        Objects.requireNonNull(user);
        return gateway.delete(user).orElse(null); //todo think about
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
