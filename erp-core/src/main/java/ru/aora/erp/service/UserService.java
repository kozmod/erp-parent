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
import ru.aora.erp.repository.crud.user.DbUserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final DbUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityModulesIdentifiersService authorityService;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    public UserService(
            DbUserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthorityModulesIdentifiersService authorityModulesIdentifiersService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityModulesIdentifiersService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        try {
            return userRepository.findByName(name)
                    .map(Objects::requireNonNull)
                    .map(dbUser -> userMapper.toUser(dbUser, authorityService.modulesAuthorities()))
                    .orElseThrow(() -> new UsernameNotFoundException(name));
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("User not found by name: ", name), ex);
        }
    }

    public List<User> loadAll() {
        return userRepository.findAll()
                .stream()
                .map(Objects::requireNonNull)
                .map(dbUser -> userMapper.toUser(dbUser, authorityService.modulesAuthorities()))
                .collect(Collectors.toList());
    }

    public void updateUser(User user) {
        try {
            DbUser dbUser = userRepository.findById(user.getId())
                    .orElseThrow(() -> new UsernameNotFoundException(Long.toString(user.getId())));
            if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
                userRepository.update(userMapper.toDbUser(user));
            } else {
                encodeUserPassword(user);
                userRepository.update(userMapper.toDbUser(user)
                );
            }
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("User not found by id"), ex);
        }
    }

    public void deleteUser(long userId) {
        userRepository.delete(userId);
    }

    private void encodeUserPassword(User user) {
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
    }
}
