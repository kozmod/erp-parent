package ru.aora.erp.model.identifier;

import ru.aora.erp.model.entity.IdAuthority;

import java.util.Map;
import java.util.Set;

public interface ModuleIdentifier {
    String moduleIdentifier();

    Set<IdAuthority> moduleAuthorities();

    Map<String, IdAuthority> moduleMapping();
}
