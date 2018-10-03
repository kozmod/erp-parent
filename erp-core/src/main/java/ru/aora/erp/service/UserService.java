package ru.aora.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aora.erp.model.action.ActionResult;
import ru.aora.erp.model.user.User;
import ru.aora.erp.model.user.UserRole;
import ru.aora.erp.model.user.UsersDto;
import ru.aora.erp.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException(name));
    }

    public ActionResult updateOrCreate(final User user) {
        return userRepository.findByName(user.getUsername())
                .map(target -> {
                    user.setId(target.getId());
                    if(user.getPassword().isEmpty()) user.setPassword(target.getPassword());
                    else encodeUserPassword(user);
                    userRepository.save(user);
                    return new ActionResult(
                            ActionResult.Result.SUCCESS,
                            "User updated"
                    );
                }).orElseGet(() -> {
                    encodeUserPassword(user);
                    userRepository.save(user);
                    return new ActionResult(
                            ActionResult.Result.SUCCESS,
                            "User created"
                    );
                });
    }

    public void deleteAllBySelected(final UsersDto userDto) {
        if(Objects.nonNull(userDto)){
           List<User> usersToDelete = userDto.getUsers().stream()
                    .filter(User::isDel)
                    .collect(Collectors.toList());
           if(!usersToDelete.isEmpty()){
               userRepository.deleteAll(usersToDelete);
           }
        }
    }

    public User emptyUser(){
                return User.builder()
                .withAccountNonExpired(true)
                .withAccountNonLocked(true)
                .withCredentialsNonExpired(true)
                .withEnabled(true)
                .build();
    }

    public UsersDto usersDto(){
        return new UsersDto(userRepository.findAll());
    }


    public List<UserRole> allRoles(){
        return Arrays.asList(UserRole.values())  ;
    }


    private void encodeUserPassword(User user) {
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
    }

}
