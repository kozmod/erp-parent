package ru.aora.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aora.erp.service.SidebarModulesIdentifiersService;

import java.util.Map;

import static ru.aora.erp.component.CoreModuleIdentifier.DASHBOARD_MAPPING;
import static ru.aora.erp.component.CoreModuleIdentifier.ROOT_MAPPING;
import static ru.aora.erp.utils.mvc.MvcUtils.redirectTo;

@Controller
public final class DashboardController {

    private static final String DASHBOARD_TEMPLATE = "dashboard";
    private static final String UI_CHANE_NODE_MODEL = "uiChaneNodeModel";

    private final SidebarModulesIdentifiersService modulesIdentifiersService;

    @Autowired
    public DashboardController(SidebarModulesIdentifiersService modulesIdentifiersService) {
        this.modulesIdentifiersService = modulesIdentifiersService;
    }

    @RequestMapping(DASHBOARD_MAPPING)
    public String dashboard(Map<String, Object> model) {
        model.put(
                UI_CHANE_NODE_MODEL,
                modulesIdentifiersService.loadAll()
        );
        return DASHBOARD_TEMPLATE;
    }

    @RequestMapping(ROOT_MAPPING)
    public String redirectToRoot() {
        return redirectTo(DASHBOARD_TEMPLATE);
    }

}
