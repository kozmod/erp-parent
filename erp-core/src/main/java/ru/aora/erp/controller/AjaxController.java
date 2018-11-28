package ru.aora.erp.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.aora.erp.model.entity.user.User;

import javax.validation.Valid;
import java.util.Map;

import static ru.aora.erp.model.mappring.HttpUtils.redirectTo;

@Controller
//@RestController
//@RequestMapping(value = "/xxx")
public class AjaxController {
//
//    @RequestMapping(method = RequestMethod.PUT)
//    public @ResponseBody MyData  xxx(@RequestBody MyData myData) {
//        System.out.println(
//                "\n_______________________________________\n" +
//                        myData +
//                        "\n_______________________________________\n"
//        );
//        return myData;
//    }

//    @RequestMapping(value = "/xxx",method = RequestMethod.GET)
//    public @ResponseBody String putMyData(Model model) {
//        System.out.println(model);
//        return model.toString();
//    }

    @RequestMapping(value = "/xxx",method = RequestMethod.PUT)
    public @ResponseBody String putMyData(
            @RequestBody User md) {

        System.out.println(md.toString());
        return "update";
    }

    @RequestMapping(value="/xxx/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteMyData(
            @PathVariable long id) {
        System.out.println(id);
        return "delete";
    }

//    @PostMapping(params = "action=Delete selected users")
//    public String deleteSelectedUsers(@ModelAttribute UsersDto dto) {
//        userService.deleteAllBySelected(dto);
//        return REDIRECT.applyTo(USERS_MANAGE_MAPPING);
//    }

}
