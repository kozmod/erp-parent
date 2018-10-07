package ru.aora.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aora.erp.service.ModulesIdentifiersService;

import java.util.Map;

import static ru.aora.erp.component.CoreModuleIdentifier.DASHBOARD_MAPPING;

@Controller
public class DashboardController {

    private static final String DASHBOARD_TEMPLATE = "dashboard";

    private static final String MODULES_REFERENCE_CHANE_ELEMENTS_MODEL = "referenceChaneElementsModel";

    private final ModulesIdentifiersService modulesIdentifiersService;

    @Autowired
    public DashboardController(ModulesIdentifiersService modulesIdentifiersService) {
        this.modulesIdentifiersService = modulesIdentifiersService;
    }

    @RequestMapping(DASHBOARD_MAPPING)
    public String dashboard(Map<String, Object> model) {
        System.out.println( modulesIdentifiersService.modulesReferenceChaneElements());
        model.put(
                MODULES_REFERENCE_CHANE_ELEMENTS_MODEL,
                modulesIdentifiersService.modulesReferenceChaneElements()
        );
        return DASHBOARD_TEMPLATE;
    }
}
