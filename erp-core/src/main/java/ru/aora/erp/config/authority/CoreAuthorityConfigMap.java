package ru.aora.erp.config.authority;

import ru.aora.erp.model.entity.business.UserAuthority;
import ru.aora.erp.presentation.controller.security.SecurityController;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


//todo test impl -> think about
public final class CoreAuthorityConfigMap {

    public static final String MODULE_NAME = "CORE";

    static final UserAuthority ADMIN = asAuthority("ADMIN");
    static final UserAuthority USER = asAuthority("USER");


    public static final Map<UserAuthority, Set<String>> urlAuthorityMap = new ConcurrentHashMap<>();

    static {
        urlAuthorityMap.put(
                ADMIN,
                Set.of(SecurityController.INCLUDE_ROOT_MAPPING) //todo add more mapping
        );
    }

    private static UserAuthority asAuthority(String authority){
        return new UserAuthority(MODULE_NAME, authority);
    }
}
