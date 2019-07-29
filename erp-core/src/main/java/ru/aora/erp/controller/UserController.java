package ru.aora.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.aora.erp.entity.dto.UserModuleAuthorityDto;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.entity.dto.UsersDto;
import ru.aora.erp.service.AuthorityModulesIdentifiersService;
import ru.aora.erp.service.UserService;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final String USERS_MAPPING = "users";
    private static final String USERS_DTO_MODEL = "usersDto";
    private static final String MODULE_AUTHORITY_DTO_LIST_MODEL = "moduleAuthorityDtoList";

    private UserService userService;
    private AuthorityModulesIdentifiersService authorityModulesIdentifiersService;

    public UserController(UserService userService,
                          AuthorityModulesIdentifiersService authorityModulesIdentifiersService
    ) {
        this.userService = userService;
        this.authorityModulesIdentifiersService = authorityModulesIdentifiersService;
    }

    @GetMapping
    public String userForm(Map<String, Object> model, Principal principal) {
        final UsersDto usersDto = UsersDto.of(userService.loadAll());
        final Collection<UserModuleAuthorityDto> userModuleAuthorityDtoList = UserModuleAuthorityDto.of(
                usersDto.getUsers(),
                authorityModulesIdentifiersService.modulesAuthorities()
        );
//        System.out.println(userModuleAuthorityDtoList);
//        System.out.println(authorityModulesIdentifiersService.modulesAuthorities());
        model.put(USERS_DTO_MODEL, usersDto);
        model.put(MODULE_AUTHORITY_DTO_LIST_MODEL, userModuleAuthorityDtoList);
        return USERS_MAPPING;
    }

    @PutMapping
    public @ResponseBody
    String putUser(@RequestBody User user) {
        System.out.println("edited user " + user.getId());
        userService.updateUser(user);
        return "update";
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "delete";
    }
}