package ru.aora.erp.component;

import org.springframework.security.core.GrantedAuthority;

public enum TestModuleAuthority implements GrantedAuthority {

    ADD, DELETE;

    @Override
    public String getAuthority() { return name();
    }
}
