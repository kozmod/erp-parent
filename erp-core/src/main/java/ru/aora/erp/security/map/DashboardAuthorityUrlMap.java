package ru.aora.erp.security.map;

import ru.aora.erp.model.entity.business.UserAuthority;
import ru.aora.erp.presentation.controller.TestController;
import ru.aora.erp.presentation.controller.dashboard.DashboardUrl;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


//todo test impl -> think about
public final class DashboardAuthorityUrlMap {

    public static final String MODULE_NAME = "CORE";

    public static final UserAuthority ADMIN = asAuthority("ADMIN");
    public static final UserAuthority USER = asAuthority("USER");

    private final Map<UserAuthority, Set<String>> authoritiesUrls = new ConcurrentHashMap<>();

    //todo add more mapping
    {
        authoritiesUrls.put(
                ADMIN,
                Set.of(
                        DashboardUrl.INCLUDE_ROOT_MAPPING, TestController.MAPPING
                )
        );
        authoritiesUrls.put(
                USER, Set.of(
                        DashboardUrl.MAPPING,
                        DashboardUrl.ROOT_MAPPING,
//                        GarantUrl.GARANT_RESULT,
//                        DashboardUrl.INCLUDE_ROOT_MAPPING,
                        TestController.MAPPING
                )
        );
    }

    public Map<UserAuthority, Set<String>> getAuthoritiesUrls() {
        return authoritiesUrls;
    }

    private static UserAuthority asAuthority(String authority) {
        return new UserAuthority(MODULE_NAME, authority);
    }

    @Override
    public String toString() {
        return "CoreAuthorityConfigMap{" +
                "urlAuthorityMap=" + authoritiesUrls +
                '}';
    }
}
