package ru.aora.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.aora.erp.model.entity.user.User;
import ru.aora.erp.model.entity.user.UsersDto;
import ru.aora.erp.service.UserService;

import java.security.Principal;
import java.util.Map;

import static ru.aora.erp.model.mappring.HttpUtils.redirectTo;

@Controller
@RequestMapping("/users")
public class UserController {

//    public static final String USERS_MANAGE_MAPPING = "/users/manage";
//
    private static final String USER_MODEL = "user";
    private static final String USERS_MAPPING = "users";
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

    @GetMapping
    public String userForm(Map<String, Object> model, Principal principal) {
//        model.put(USER_MODEL, userService.emptyUser());
        model.put(
                USERS_DTO_MODEL,
                UsersDto.of(userService.loadAll())
        );
//        model.put(ROLES_MODEL, userService.allRoles());
//        model.put(USER_NAME_MODEL, principal.getName());
        return USERS_MAPPING;
    }

////    @RequestMapping("/xxx")
////            ( params = "update_user=update_user")
////    public String add( @RequestBody UserX customer, BindingResult bindingResult) {
//    public String add(Map<String, Object> model) {
////    public String add(@ModelAttribute UsersDto usersDto, BindingResult bindingResult) {
////        userService.updateOrCreate(user);
//        System.out.println(
//                "\n_______________________________________\n" +
//                        "xxxx" +
//                        "\n_______________________________________\n"
//        );
//        return redirectTo(USERS_MAPPING);
//    }

//    @PostMapping(params = "action=Delete selected users")
//    public String deleteSelectedUsers(@ModelAttribute UsersDto dto) {
//        userService.deleteAllBySelected(dto);
//        return REDIRECT.applyTo(USERS_MANAGE_MAPPING);
//    }


    private User emptyUser() {
        return User.builder()
                .withAccountNonExpired(true)
                .withAccountNonLocked(true)
                .withCredentialsNonExpired(true)
                .withEnabled(true)
                .build();
    }
}
