package ru.aora.erp.repository.gateway;

import ru.aora.erp.domain.UserGateway;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.model.entity.db.Deactivatable;
import ru.aora.erp.model.entity.db.user.DbModuleRolePair;
import ru.aora.erp.model.entity.db.user.DbUser;
import ru.aora.erp.model.entity.mapper.UserMapper;
import ru.aora.erp.repository.jpa.JpaUserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.UnaryOperator;

import static ru.aora.erp.model.entity.db.Deactivatable.INACTIVE_ENTITY_FLAG;

@Transactional
public class DbUserGateway implements UserGateway {

    private final JpaUserRepository userRepository;
    private final DbModuleRolePairGateway moduleRolePairGateway;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public DbUserGateway(
            JpaUserRepository userRepository,
            DbModuleRolePairGateway moduleRolePairGateway) {
        this.userRepository = userRepository;
        this.moduleRolePairGateway = moduleRolePairGateway;
    }

    @Override
    public Optional<User> findByName(String name) {
        Optional<DbUser> resOpt = userRepository.findActiveByName(name).filter(Deactivatable::isActive);
        if (resOpt.isPresent()) {
            var res = resOpt.get();
            res.setAuthorities(
                    moduleRolePairGateway.addModuleRoleNameByIdFunction().apply(res.getAuthorities())
            );
            return Optional.of(userMapper.toUser(res));
        }
        return Optional.empty();
    }

    @Override
    public List<User> loadAll() {
        List<User> res = new ArrayList<>();
        UnaryOperator<Collection<DbModuleRolePair>> valueSetter = moduleRolePairGateway.addModuleRoleNameByIdFunction();
        for (var dbUser : userRepository.findAll()) {
            if (dbUser.isActive()) {
                dbUser.setAuthorities(
                        valueSetter.apply(dbUser.getAuthorities())
                );
                res.add(userMapper.toUser(dbUser));
            }
        }
        return res;
    }

    @Override
    public User create(User user) {
        Optional<DbUser> target = userRepository.findActiveByName(user.getUsername());
        if (target.isPresent()) {
            throw new IllegalArgumentException(
                    String.format("User with user name [%s] exists", user.getUsername())
            );
        }
        var source = userMapper.toDbUser(user);
        source.setAuthorities(
                moduleRolePairGateway.tryCreateAuthoritiesAndIds(source.getAuthorities())
        );
        var res = userRepository.save(source);
        return userMapper.toUser(res);
    }


    @Override
    public Optional<User> update(User user) {
        Optional<DbUser> target = userRepository.findActiveByName(user.getUsername())
                .filter(Deactivatable::isActive)
                .map(this::setDeactivated);
        if (target.isPresent()) {
            userRepository.save(target.get());
            DbUser source = userMapper.toDbUser(user);
            source.setAuthorities(
                    moduleRolePairGateway.prepareRoleToUpdate(source.getAuthorities(), source.getId())
            );
            DbUser res = userRepository.save(source);
            return Optional.of(userMapper.toUser(res));
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(String name) {
        Optional<DbUser> targetOpt = userRepository.findActiveByName(name);
        if (targetOpt.isPresent()) {
            var target = targetOpt.get();
            setDeactivated(target);
            target.setAuthorities(
                    moduleRolePairGateway.deactivateAll(target.getAuthorities())
            );
            var res = userRepository.save(setDeactivated(target));
            return Optional.ofNullable(userMapper.toUser(res));
        }
        return Optional.empty();
    }

    private DbUser setDeactivated(DbUser user) {
        return Objects.requireNonNull(user)
                .setActiveStatus(INACTIVE_ENTITY_FLAG)
                .setDeactivationDate(LocalDateTime.now());
    }
}
