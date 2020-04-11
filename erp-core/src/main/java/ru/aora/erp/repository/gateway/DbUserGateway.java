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
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DbUserGateway implements UserGateway {

    private final JpaUserRepository userRepository;
    private final JpaAuthorityRepository authorityRepository;
    private final JpaSubAuthorityRepository subAuthorityRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public DbUserGateway(
            JpaUserRepository userRepository,
            JpaAuthorityRepository authorityRepository,
            JpaSubAuthorityRepository subAuthorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.subAuthorityRepository = subAuthorityRepository;
    }



    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name)
                .map(userMapper::toUser)
                .or(Optional::empty);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.empty(); //todo new to discus
    }

    @Override
    public Optional<User> delete(User user) {
        return Optional.empty(); //todo new to discus
    }


    private DbSubAuthority createSubAuthorityIfNotFound(String name) {
        return subAuthorityRepository.findByName(name)
                .orElseGet(() -> subAuthorityRepository.save(new DbSubAuthority().setName(name)));
    }

    private DbAuthority createAuthorityIfNotFound(String name, Collection<DbSubAuthority> sub) {
        return authorityRepository.findByName(name)
                .orElseGet(() -> authorityRepository.save(new DbAuthority().setName(name).setSubAuthorities(sub)));
    }

}
