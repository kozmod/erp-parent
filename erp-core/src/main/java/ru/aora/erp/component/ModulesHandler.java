package ru.aora.erp.component;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aora.erp.model.identifier.ModuleIdentifier;

import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

@Component
public class ModulesHandler {

    private final List<ModuleIdentifier> moduleIdentifiers;

    @Autowired
    public ModulesHandler(List<ModuleIdentifier> moduleIdentifiers) {
        this.moduleIdentifiers = moduleIdentifiers;
    }

    public List<String> foundModulesMapping(){
        if(isNotEmpty(moduleIdentifiers)){
            final List<String> list =  Lists.newArrayList();
            moduleIdentifiers.forEach(moduleIdentifier ->
                            moduleIdentifier.moduleMapping().ifPresent(list::add)
            );
            return list;
        }
        return Lists.newArrayList();
    }
}
