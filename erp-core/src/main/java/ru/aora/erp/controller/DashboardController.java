package ru.aora.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aora.erp.service.ModulesIdentifiersService;

import java.util.Map;

import static ru.aora.erp.component.CoreModuleIdentifier.DASHBOARD_MAPPING;
import static ru.aora.erp.component.CoreModuleIdentifier.ROOT_MAPPING;
import static ru.aora.erp.model.mappring.HttpUtils.redirectTo;

@Controller
public class DashboardController {

    private static final String DASHBOARD_TEMPLATE = "dashboard";

    private static final String MODULES_REFERENCE_CHANE_ELEMENTS_MODEL = "uiChaneNodeModel";

    private final ModulesIdentifiersService modulesIdentifiersService;

    @Autowired
    public DashboardController(ModulesIdentifiersService modulesIdentifiersService) {
        this.modulesIdentifiersService = modulesIdentifiersService;
    }

    @RequestMapping(DASHBOARD_MAPPING)
    public String dashboard(Map<String, Object> model) {
        model.put(
                MODULES_REFERENCE_CHANE_ELEMENTS_MODEL,
                modulesIdentifiersService.modulesReferenceChaneElements()
        );
        return DASHBOARD_TEMPLATE;
    }

    @RequestMapping(ROOT_MAPPING)
    public String redirectToRoot() {
        return redirectTo(DASHBOARD_TEMPLATE);
    }

}
