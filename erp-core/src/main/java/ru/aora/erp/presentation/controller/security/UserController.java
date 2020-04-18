package ru.aora.erp.presentation.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.aora.erp.domain.service.user.UserAuthorityCacheService;
import ru.aora.erp.presentation.controller.exception.DtoValidationException;
import ru.aora.erp.presentation.entity.dto.user.UserIdModuleAuthorityDto;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.presentation.entity.dto.user.UsersDto;
import ru.aora.erp.domain.service.user.UserService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static ru.aora.erp.presentation.controller.security.UserController.MAPPING;

@Controller
@RequestMapping(MAPPING)
public final class UserController {

    public static final String MAPPING = "/user";

    private static final String USERS_TEMPLATE = "users";
    private static final String USERS_DTO_MODEL = "usersDto";
    private static final String MODULE_AUTHORITY_DTO_LIST_MODEL = "moduleAuthorityDtoList";

    private final UserService userService;
    private final UserAuthorityCacheService authorityCache;

    public UserController(
            UserService userService,
            UserAuthorityCacheService authorityCache
    ) {
        this.userService = userService;
        this.authorityCache = authorityCache;
    }

    @GetMapping
    public String getUsers(Map<String, Object> model) {
        List<User> users = userService.loadAll();
        UsersDto usersDto = UsersDto.of(users);
        Collection<UserIdModuleAuthorityDto> userModuleAuthorityDtoList = UserIdModuleAuthorityDto.of(
                usersDto.getUsers(),
                authorityCache.allAuthorities()
        );
        model.put(USERS_DTO_MODEL, usersDto);
        model.put(MODULE_AUTHORITY_DTO_LIST_MODEL, userModuleAuthorityDtoList);
        return USERS_TEMPLATE;
    }

    @PutMapping
    public @ResponseBody
    String updateUser(@RequestBody User user, BindingResult bindingResult) {
        DtoValidationException.throwIfHasErrors(bindingResult);
        return userService.update(user).getMsg();
    }

    @PostMapping
    public @ResponseBody
    String createUser(@RequestBody User user, BindingResult bindingResult) {
        DtoValidationException.throwIfHasErrors(bindingResult);
        userService.create(user);
        return "User created";
    }

    @DeleteMapping("/{name}")
    public @ResponseBody
    String deleteUser(@PathVariable String name, BindingResult bindingResult) {
        DtoValidationException.throwIfHasErrors(bindingResult);
        return userService.delete(name).getMsg();
    }
}