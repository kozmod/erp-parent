package ru.aora.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aora.erp.model.entity.db.DbUser;
import ru.aora.erp.model.entity.converter.UserConverter;
import ru.aora.erp.model.entity.user.User;
import ru.aora.erp.repository.crud.user.DbUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final DbUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;

    @Autowired
    public UserService(
            DbUserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthorityModulesIdentifiersService authorityModulesIdentifiersService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userConverter = new UserConverter(authorityModulesIdentifiersService.modulesAuthorities());
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        try {
            return userRepository.findByName(name)
                    .map(userConverter::convert)
                    .orElseThrow(() -> new UsernameNotFoundException(name));
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("User not found by name: ", name), ex);
        }
    }

    public List<User> loadAll() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::convert)
                .collect(Collectors.toList());
    }

    public void updateUser(User user) {
        try {
            DbUser dbUser = userRepository.findById(user.getId())
                    .orElseThrow(() -> new UsernameNotFoundException(Long.toString(user.getId())));
            if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
                userRepository.update(userConverter.convert(user));
            } else {
                encodeUserPassword(user);
                userRepository.update(userConverter.convert(user));
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
