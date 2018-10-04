package ru.aora.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static ru.aora.erp.component.CoreModuleIdentifier.LOGIN_MAPPING;

@Controller
public class SecurityController {

    private static final String LOGIN_TEMPLATE = "login";

    @RequestMapping(LOGIN_MAPPING)
    public String login() {
        return LOGIN_TEMPLATE;
    }
}
