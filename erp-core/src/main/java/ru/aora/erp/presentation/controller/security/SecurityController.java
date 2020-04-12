package ru.aora.erp.presentation.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public final class SecurityController {

    public static final String INCLUDE_ROOT_MAPPING = "/**";
    public static final String LOGOUT_MAPPING = "/logout";
    public static final String LOGIN_MAPPING = "/login";
    public static final String ROOT_MAPPING = "/";

    private static final String LOGIN_TEMPLATE = "login";

    @RequestMapping(LOGIN_MAPPING)
    public String login() {
        return LOGIN_TEMPLATE;
    }
}
