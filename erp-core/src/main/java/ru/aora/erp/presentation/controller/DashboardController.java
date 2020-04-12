package ru.aora.erp.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aora.erp.presentation.presenter.AllSidebarPresenter;

import java.util.Map;

import static ru.aora.erp.presentation.controller.security.SecurityController.ROOT_MAPPING;
import static ru.aora.erp.utils.mvc.MvcUtils.redirectTo;


@Controller
public final class DashboardController {

    public static final String DASHBOARD_MAPPING = "/dashboard";

    private static final String DASHBOARD_TEMPLATE = "dashboard";
    private static final String UI_CHANE_NODE_MODEL = "uiChaneNodeModel";

    private final AllSidebarPresenter sidebarPresenter;

    public DashboardController(AllSidebarPresenter sidebarPresenter) {
        this.sidebarPresenter = sidebarPresenter;
    }

    @RequestMapping(DASHBOARD_MAPPING)
    public String dashboard(Map<String, Object> model) {
        model.put(
                UI_CHANE_NODE_MODEL,
                sidebarPresenter.allRootNodes()
        );
        return DASHBOARD_TEMPLATE;
    }

    @RequestMapping(ROOT_MAPPING)
    public String redirectToRoot() {
        return redirectTo(DASHBOARD_TEMPLATE);
    }
}
