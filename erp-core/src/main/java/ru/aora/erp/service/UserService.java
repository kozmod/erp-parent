package ru.aora.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aora.erp.model.entity.db.DbUser;
import ru.aora.erp.model.entity.mapper.UserMapper;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.repository.jpa.JpaUserRepository;
import ru.aora.erp.utils.common.CommonUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final JpaUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityModulesIdentifiersService authorityService;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    public UserService(
            JpaUserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthorityModulesIdentifiersService authorityModulesIdentifiersService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityModulesIdentifiersService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        CommonUtils.requiredNotBlank(name);
        return userRepository.findByName(name)
                .map(userMapper::toUser)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by name: " + name));
    }

    public List<User> loadAll() {
        return userRepository.findAll()
                .stream()
                .map(Objects::requireNonNull)
                .map(userMapper::toUser)
                .collect(Collectors.toList());
    }

    public User updateUser(User user) {
        Objects.requireNonNull(user);
        DbUser dbUser = userRepository
                .findById(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found by id: " + user.getId()));
        tryEncodeUserPassword(user, dbUser);
        DbUser updatedUser = userRepository.save(userMapper.toDbUser(user));
        return userMapper.toUser(updatedUser);
    }

    public Long deleteUser(Long userId) {
        Objects.requireNonNull(userId);
        userRepository.deleteById(userId); //todo -> fix return ///
        return userId;
    }

    private void tryEncodeUserPassword(User user, DbUser dbUser) {
        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }
}
