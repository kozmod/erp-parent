package ru.aora.erp.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;
import ru.aora.erp.model.entity.IdAuthority;
import ru.aora.erp.model.entity.converter.ModuleConverter;
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

    public List<ModuleIdentifier> loadAll() {
        return moduleIdentifiers;
    }

    public List<IdAuthority> modulesAuthorities() {
        final List<IdAuthority> authorities = new ArrayList<>();
        for (ModuleIdentifier moduleIdentifier : moduleIdentifiers) {
            authorities.addAll(moduleIdentifier.moduleAuthorities());
        }
        return authorities;
    }

}
