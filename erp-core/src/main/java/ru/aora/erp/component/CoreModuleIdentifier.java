package ru.aora.erp.component;

import org.springframework.stereotype.Component;
import ru.aora.erp.model.entity.business.IdAuthority;
import ru.aora.erp.model.identifier.ModuleIdentifier;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public final class CoreModuleIdentifier implements ModuleIdentifier {

    public static final String DASHBOARD_MAPPING = "/dashboard";

    public static final String INCLUDE_ROOT_MAPPING = "/**";
    public static final String LOGOUT_MAPPING = "/logout";
    public static final String LOGIN_MAPPING = "/login";
    public static final String ROOT_MAPPING = "/";

    private Map<String, IdAuthority> mappingAuthorities;


    public CoreModuleIdentifier() {
        this.mappingAuthorities = new HashMap<>();
    }

    @PostConstruct
    private void init() {
        prepareModuleAuthority();
    }

    @Override
    public String moduleIdentifier() {
        return CoreModuleIdentifier.class.toString();
    }

    @Override
    public Set<IdAuthority> moduleAuthorities() {
        return new HashSet<>(
                Arrays.asList(CoreModuleAuthority.values())
        );
    }

    @Override
    public Map<String, IdAuthority> moduleMapping() {
        return mappingAuthorities;
    }

    private void prepareModuleAuthority() {
//        mappingAuthorities.put(LOGOUT_MAPPING,
//                new GrantedAuthority[]{CoreModuleAuthority.ADMIN, CoreModuleAuthority.USER});
//        mappingAuthorities.put(LOGIN_MAPPING,
//                new GrantedAuthority[]{CoreModuleAuthority.ADMIN, CoreModuleAuthority.USER});
//        mappingAuthorities.put(ROOT_MAPPING,
//                new GrantedAuthority[]{CoreModuleAuthority.ADMIN, CoreModuleAuthority.USER});
//        mappingAuthorities.put(DASHBOARD_MAPPING,
//                new GrantedAuthority[]{CoreModuleAuthority.ADMIN, CoreModuleAuthority.USER});
    }
}
