package ru.aora.erp.component;

import org.springframework.stereotype.Component;
import ru.aora.erp.model.identifier.ModuleIdentifier;
import ru.aora.erp.model.identifier.chane.ReferenceChaneElement;

@Component
public class CoreModuleIdentifier implements ModuleIdentifier {

    public static final String DASHBOARD_MAPPING = "/dashboard";

    public static final String INCLUDE_ROOT_MAPPING = "/**";
    public static final String LOGOUT_MAPPING = "/logout";
    public static final String LOGIN_MAPPING = "/login";
    public static final String ROOT_MAPPING = "/";

//    TODO: think about differentModules

    @Override
    public String moduleIdentifier() {
        return CoreModuleIdentifier.class.toString();
    }

}
