package ru.aora.erp.repository.gateway;

import org.springframework.stereotype.Service;
import ru.aora.erp.domain.UserGateway;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.model.entity.db.user.DbAuthority;
import ru.aora.erp.model.entity.db.user.DbSubAuthority;
import ru.aora.erp.model.entity.db.user.DbUser;
import ru.aora.erp.model.entity.mapper.UserMapper;
import ru.aora.erp.repository.jpa.JpaAuthorityRepository;
import ru.aora.erp.repository.jpa.JpaSubAuthorityRepository;
import ru.aora.erp.repository.jpa.JpaUserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ru.aora.erp.repository.gateway.GatewayUtils.*;

@Service
@Transactional
public class DbUserGateway implements UserGateway {

    private final JpaUserRepository userRepository;
    private final JpaAuthorityRepository authorityRepository;
    private final JpaSubAuthorityRepository subAuthorityRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    private final Function<List<DbAuthority>, Map<String, DbAuthority>> gropeAuthorityByName = groupFunctions(DbAuthority::getName);
    private final Function<List<DbSubAuthority>, Map<String, DbSubAuthority>> gropeSubAuthorityByName = groupFunctions(DbSubAuthority::getName);

    public DbUserGateway(
            JpaUserRepository userRepository,
            JpaAuthorityRepository authorityRepository,
            JpaSubAuthorityRepository subAuthorityRepository

    ) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.subAuthorityRepository = subAuthorityRepository;
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findActiveByName(name)
                .filter(this::isActive)
                .map(userMapper::toUser)
                .or(Optional::empty);
    }

    @Override
    public List<User> loadAll() {
        return userRepository.findAll()
                .stream()
                .filter(this::isActive)
                .map(userMapper::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public User create(User user) {
        Optional<DbUser> target = userRepository.findActiveByName(user.getUsername());
        if (target.isPresent()) {
            throw new IllegalArgumentException(
                    String.format("User with user name [%s] exists", user.getUsername())
            );
        }
        DbUser source = userMapper.toDbUser(user);
        tryCreateAuthoritiesAndSetId(source.getAuthorities());
        DbUser res = userRepository.save(source);
        return userMapper.toUser(res);
    }


    @Override
    public Optional<User> update(User user) {
        Optional<DbUser> target = userRepository.findActiveByName(user.getUsername())
                .filter(this::isActive)
                .map(this::setDeactivated);
        if (target.isPresent()) {
            userRepository.save(target.get());
            DbUser source = userMapper.toDbUser(user);
            tryCreateAuthoritiesAndSetId(source.getAuthorities());
            DbUser res = userRepository.save(source);
            return Optional.of(userMapper.toUser(res));
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(User user) {
        Optional<DbUser> target = userRepository.findActiveByName(user.getUsername());
        if (target.isPresent()) {
            DbUser res = userRepository.save(setDeactivated(target.get()));
            return Optional.ofNullable(userMapper.toUser(res));
        }
        return Optional.empty();
    }

    private boolean isActive(DbUser user) {
        return ACTIVE_ENTITY_FLAG.equals(user.getDeactivated())
                && user.getDeactivationDate() == null;
    }

    private DbUser setDeactivated(DbUser user) {
        return Objects.requireNonNull(user)
                .setDeactivated(INACTIVE_ENTITY_FLAG)
                .setDeactivationDate(LocalDateTime.now());
    }

    private void tryCreateAuthoritiesAndSetId(Collection<DbAuthority> authorities) {
        if (authorities == null) {
            return;
        }
        Map<String, DbSubAuthority> existsSubAuthorities = gropeSubAuthorityByName
                .apply(subAuthorityRepository.findAll());
        Map<String, DbAuthority> existsAuthorities = gropeAuthorityByName
                .apply(authorityRepository.findAll());

        for (DbAuthority authority : authorities) {
            for (DbSubAuthority subAuthority : authority.getSubAuthorities()) {
                DbSubAuthority existsSub = existsSubAuthorities.computeIfAbsent(
                        subAuthority.getName(),
                        name -> subAuthorityRepository.save(subAuthority)
                );
                subAuthority.setId(existsSub.getName());
            }
            DbAuthority exists = existsAuthorities.computeIfAbsent(
                    authority.getName(),
                    name -> authorityRepository.save(authority)
            );
            authority.setId(exists.getId());
        }
    }
}
