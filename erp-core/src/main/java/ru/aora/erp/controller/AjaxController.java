package ru.aora.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import static ru.aora.erp.model.mappring.HttpUtils.redirectTo;

@Controller
public class AjaxController {

//    @RequestMapping(value = "/xxx",method = RequestMethod.POST)
//    public String xxx() {
//        System.out.println(
//                "\n_______________________________________\n" +
//                        "Z" +
//                        "\n_______________________________________\n"
//        );
//        return redirectTo("dashboard");
//    }

//    @PostMapping(params = "action=Delete selected users")
//    public String deleteSelectedUsers(@ModelAttribute UsersDto dto) {
//        userService.deleteAllBySelected(dto);
//        return REDIRECT.applyTo(USERS_MANAGE_MAPPING);
//    }
}
