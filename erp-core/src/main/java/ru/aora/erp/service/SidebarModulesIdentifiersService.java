package ru.aora.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aora.erp.model.identifier.SidebarModuleIdentifier;
import ru.aora.erp.model.identifier.chane.UiChaneNode;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Service
public class SidebarModulesIdentifiersService {

    private final List<SidebarModuleIdentifier> sidebarModuleIdentifiers;

    @Autowired
    public SidebarModulesIdentifiersService(List<SidebarModuleIdentifier> sidebarModuleIdentifiers) {
        this.sidebarModuleIdentifiers = sidebarModuleIdentifiers;
    }

    public List<UiChaneNode> loadAll() {
        final List<UiChaneNode> list = new ArrayList<>();
        if (isNotEmpty(sidebarModuleIdentifiers)) {
            sidebarModuleIdentifiers.forEach(moduleIdentifier ->
                    list.add(moduleIdentifier.firstReferenceChaneElement())
            );
        }
        return list;
    }
}
