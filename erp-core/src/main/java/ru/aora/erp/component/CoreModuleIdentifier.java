package ru.aora.erp.component;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import ru.aora.erp.model.identifier.ModuleIdentifier;

import java.util.List;
import java.util.Optional;

@Component
public class CoreModuleIdentifier implements ModuleIdentifier {

    public static final String DASHBOARD_MAPPING = "/dashboard";

    public static final String INCLUDE_ROOT_MAPPING = "/**";
    public static final String LOGOUT_MAPPING = "/logout";
    public static final String LOGIN_MAPPING = "/login";
    public static final String ROOT_MAPPING = "/";

    @Override
    public String moduleIdentifier() {
        return CoreModuleIdentifier.class.toString();
    }

    @Override
    public Optional<String> moduleMapping() {
        return Optional.empty();
    }

    @Override
    public List<String> subMapping() {
        return Lists.newArrayList(
                INCLUDE_ROOT_MAPPING,
                LOGOUT_MAPPING,
                LOGIN_MAPPING,
                ROOT_MAPPING
        );
    }

    @Override
    public int moduleId() {
        return 0;
    }
}
