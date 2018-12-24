//package ru.aora.erp.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import ru.aora.erp.model.entity.user.User;
//import ru.aora.erp.service.UserService;
//
//
//@Controller
////@RestController
////@RequestMapping(value = "/xxx")
//public class AjaxController {
//
//
//    private UserService userService;
//    //
//    @Autowired
//    public AjaxController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @RequestMapping(value = "/xxx",method = RequestMethod.PUT)
//    public @ResponseBody String putMyData(@RequestBody User md) {
//        userService.updateUser(md);
//        return "update";
//    }
//
//    @RequestMapping(value="/xxx/{id}", method = RequestMethod.DELETE)
//    public @ResponseBody String deleteMyData(@PathVariable long id) {
//        userService.deleteUser(id);
//        return "delete";
//    }
//
////    @PostMapping(params = "action=Delete selected users")
////    public String deleteSelectedUsers(@ModelAttribute UsersDto dto) {
////        userService.deleteAllBySelected(dto);
////        return REDIRECT.applyTo(USERS_MANAGE_MAPPING);
////    }
//
//}
