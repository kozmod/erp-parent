package ru.aora.erp.presentation.controller.dashboard;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aora.erp.domain.service.user.UserService;
import ru.aora.erp.presentation.presenter.AllSidebarPresenter;

import java.util.Map;

import static ru.aora.erp.utils.mvc.MvcUtils.redirectTo;


@Controller
public final class DashboardController {

    private static final String DASHBOARD_TEMPLATE = "dashboard";
    private static final String LOGIN_TEMPLATE = "login";

    private static final String UI_CHANE_NODE_MODEL = "uiChaneNodeModel";

    private final AllSidebarPresenter sidebarPresenter;
    private final UserService userService;

    public DashboardController(AllSidebarPresenter sidebarPresenter, UserService userService) {
        this.sidebarPresenter = sidebarPresenter;
        this.userService = userService;
    }

    @RequestMapping(DashboardUrl.MAPPING)
    public String dashboard( Map<String, Object> model) {
        model.put(
                UI_CHANE_NODE_MODEL,
                sidebarPresenter.allRootNodes()
        );
        return DASHBOARD_TEMPLATE;
    }

    @RequestMapping(DashboardUrl.ROOT_MAPPING)
    public String redirectToRoot() {
        return redirectTo(DASHBOARD_TEMPLATE);
    }

    @RequestMapping(DashboardUrl.LOGIN_MAPPING)
    public String login() {
        return LOGIN_TEMPLATE;
    }
}
