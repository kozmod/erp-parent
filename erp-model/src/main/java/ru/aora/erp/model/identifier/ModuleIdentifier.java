package ru.aora.erp.model.identifier;

import org.springframework.security.core.GrantedAuthority;

import java.util.Map;
import java.util.Set;

public interface ModuleIdentifier {
    String moduleIdentifier();
    Set<GrantedAuthority> moduleAuthorities();
    Map<String, GrantedAuthority[]> moduleMapping();
}
