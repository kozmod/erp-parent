package ru.aora.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.aora.erp.model.identifier.ModuleIdentifier;

import java.util.ArrayList;
import java.util.List;


@Service
public class AuthorityModulesIdentifiersService {

    private final List<ModuleIdentifier> moduleIdentifiers;

    @Autowired
    public AuthorityModulesIdentifiersService(List<ModuleIdentifier> moduleIdentifiers) {
        this.moduleIdentifiers = moduleIdentifiers;
    }

    public List<ModuleIdentifier> moduleIdentifiers() {
        return moduleIdentifiers;
    }

    public List<GrantedAuthority> modulesAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (ModuleIdentifier moduleIdentifier : moduleIdentifiers) {
            authorities.addAll(moduleIdentifier.moduleAuthorities());
        }
        return authorities;
    }
}
