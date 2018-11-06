package ru.aora.erp.component;

import org.springframework.security.core.GrantedAuthority;

public enum CoreModuleAuthority implements GrantedAuthority {

    USER, ADMIN;

    @Override
    public String getAuthority() { return name(); }
}
