package ru.aora.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aora.erp.model.entity.user.UsersDto;
import ru.aora.erp.service.UserService;

import java.security.Principal;
import java.util.Map;

@Controller
public class UserController {

//    public static final String USERS_MANAGE_MAPPING = "/users/manage";
//
    private static final String USER_MODEL = "user";
    private static final String USERS_DTO_MODEL = "usersDto";
//    private static final String USER_NAME_MODEL = "username";
//    private static final String ROLES_MODEL = "roles";
//
//    private static final String USERS_TEMPLATE = "users";
//
    private UserService userService;
//
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/xxx")
    public String userForm(Map<String, Object> model, Principal principal) {
//        model.put(USER_MODEL, userService.emptyUser());
        model.put(
                USERS_DTO_MODEL,
                UsersDto.of(userService.loadAll())
        );
//        model.put(ROLES_MODEL, userService.allRoles());
//        model.put(USER_NAME_MODEL, principal.getName());
        return "users";
    }

//    @PostMapping(params = "action=Update or Create")
//    public String add(@ModelAttribute User user) {
//        userService.updateOrCreate(user);
//        return REDIRECT.applyTo(USERS_MANAGE_MAPPING);
//    }
//
//    @PostMapping(params = "action=Delete selected users")
//    public String deleteSelectedUsers(@ModelAttribute UsersDto dto) {
//        userService.deleteAllBySelected(dto);
//        return REDIRECT.applyTo(USERS_MANAGE_MAPPING);
//    }
}
