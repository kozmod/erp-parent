package ru.aora.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//TODO: remove
@Controller
public class AjaxController {

    @RequestMapping("/xxx")
    public String t() {
        return "users";
    }
}
