package ru.aora.erp.component;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import ru.aora.erp.model.identifier.ModuleIdentifier;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Component
public class CoreModuleIdentifier implements ModuleIdentifier {

    public static final String DASHBOARD_MAPPING = "/dashboard";

    public static final String INCLUDE_ROOT_MAPPING = "/**";
    public static final String LOGOUT_MAPPING = "/logout";
    public static final String LOGIN_MAPPING = "/login";
    public static final String ROOT_MAPPING = "/";

    private Map<String, GrantedAuthority[]> mappingAuthorities;


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
    public Set<GrantedAuthority> moduleAuthorities() {
        return new HashSet<>(
                Arrays.asList(CoreModuleAuthority.values())
        );
    }

    @Override
    public Map<String, GrantedAuthority[]> moduleMapping() {
        return mappingAuthorities;
    }

    private void prepareModuleAuthority() {
        mappingAuthorities.put(LOGOUT_MAPPING,
                new GrantedAuthority[]{CoreModuleAuthority.ADMIN, CoreModuleAuthority.USER});
        mappingAuthorities.put(LOGIN_MAPPING,
                new GrantedAuthority[]{CoreModuleAuthority.ADMIN, CoreModuleAuthority.USER});
        mappingAuthorities.put(ROOT_MAPPING,
                new GrantedAuthority[]{CoreModuleAuthority.ADMIN, CoreModuleAuthority.USER});
        mappingAuthorities.put(DASHBOARD_MAPPING,
                new GrantedAuthority[]{CoreModuleAuthority.ADMIN, CoreModuleAuthority.USER});
    }
}
