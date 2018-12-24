package ru.aora.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.aora.erp.model.entity.user.User;
import ru.aora.erp.model.entity.user.UsersDto;
import ru.aora.erp.service.UserService;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final String USERS_MAPPING = "users";
    private static final String USERS_DTO_MODEL = "usersDto";

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userForm(Map<String, Object> model, Principal principal) {
        model.put(
                USERS_DTO_MODEL,
                UsersDto.of(userService.loadAll())
        );
        return USERS_MAPPING;
    }

    @PutMapping
    public @ResponseBody
    String putMyData(@RequestBody User md) {
        userService.updateUser(md);
        return "update";
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "delete";
    }
}
