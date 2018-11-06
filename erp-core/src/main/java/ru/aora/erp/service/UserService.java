package ru.aora.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aora.erp.model.entity.db.UserConverter;
import ru.aora.erp.model.entity.user.User;
import ru.aora.erp.repository.DbUserRepository;

import java.util.Arrays;

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
        return userRepository.findByName(name)
                .map(userConverter::convert)
                .orElseThrow(() -> new UsernameNotFoundException(name));
    }

    //    public ActionResult updateOrCreate(final User user) {
//        return userRepository.findByName(user.getUsername())
//                .map(target -> {
//                    user.setId(target.getId());
//                    if(user.getPassword().isEmpty()) user.setPassword(target.getPassword());
//                    else encodeUserPassword(user);
//                    userRepository.save(user);
//                    return new ActionResult(
//                            ActionResult.Result.SUCCESS,
//                            "User updated"
//                    );
//                }).orElseGet(() -> {
//                    encodeUserPassword(user);
//                    userRepository.save(user);
//                    return new ActionResult(
//                            ActionResult.Result.SUCCESS,
//                            "User created"
//                    );
//                });
//    }
//
//    public void deleteAllBySelected(final UsersDto userDto) {
//        if(Objects.nonNull(userDto)){
//           List<User> usersToDelete = userDto.getUsers().stream()
//                    .filter(User::isDel)
//                    .collect(Collectors.toList());
//           if(!usersToDelete.isEmpty()){
//               userRepository.deleteAll(usersToDelete);
//           }
//        }
//    }
//

    public User emptyUser() {
        return User.builder()
                .withAccountNonExpired(true)
                .withAccountNonLocked(true)
                .withCredentialsNonExpired(true)
                .withEnabled(true)
                .build();
    }

//    public UsersDto usersDto(){
//        return new UsersDto(userRepository.findAll());
//    }

    private void encodeUserPassword(User user) {
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
    }

}
