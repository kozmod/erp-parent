package ru.aora.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static ru.aora.erp.component.CoreModuleIdentifier.DASHBOARD_MAPPING;

@Controller
public class DashboardController {

    private static final String DASHBOARD_TEMPLATE = "dashboard";

    @RequestMapping(DASHBOARD_MAPPING)
    public String login() {
        return DASHBOARD_TEMPLATE;
    }
}
