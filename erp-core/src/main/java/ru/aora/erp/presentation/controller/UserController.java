package ru.aora.erp.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.aora.erp.domain.service.user.UserAuthorityCacheService;
import ru.aora.erp.presentation.entity.dto.user.UserIdModuleAuthorityDto;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.presentation.entity.dto.user.UsersDto;
import ru.aora.erp.domain.service.user.UserService;
import ru.aora.erp.utils.result.OperationResult;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static ru.aora.erp.presentation.controller.UserController.MAPPING;

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
    public String userForm(Map<String, Object> model) {
        OperationResult<List<User>, Exception> operationResult = OperationResult.lift(() -> userService.loadAll()).get();
        operationResult.getResult()
                .ifPresent(result -> {
                    UsersDto usersDto = UsersDto.of(result);
                    Collection<UserIdModuleAuthorityDto> userModuleAuthorityDtoList = UserIdModuleAuthorityDto.of(
                            usersDto.getUsers(),
                            authorityCache.allAuthorities()
                    );
                    model.put(USERS_DTO_MODEL, usersDto);
                    model.put(MODULE_AUTHORITY_DTO_LIST_MODEL, userModuleAuthorityDtoList);
                });
        operationResult.getFailure().ifPresent(RuntimeException::new);
        return USERS_TEMPLATE;
    }

    @PutMapping
    public @ResponseBody String putUser(@RequestBody User user) {
        OperationResult<User, Exception> operationResult = OperationResult.get(() -> userService.updateUser(user));
        operationResult.getFailure().ifPresent(RuntimeException::new);
        return "update";
    }

//    @DeleteMapping("/{id}") todo add
//    public @ResponseBody String deleteUser(@PathVariable long id) {
//        OperationResult<Long, Exception> operationResult = OperationResult.get(() -> userService.deleteUser(id));
//        operationResult.getFailure().ifPresent(RuntimeException::new);
//        return "delete";
//    }
}