package ru.aora.erp.presentation.entity.dto.user;

import java.util.Map;
import java.util.StringJoiner;

public final class ModuleAuthorityDto {
    private String moduleName;
    Map<String, Boolean> moduleMap;

    public ModuleAuthorityDto(String moduleName, Map<String, Boolean> moduleMap) {
        this.moduleName = moduleName;
        this.moduleMap = moduleMap;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Map<String, Boolean> getModuleMap() {
        return moduleMap;
    }

    public void setModuleMap(Map<String, Boolean> moduleMap) {
        this.moduleMap = moduleMap;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", ModuleAuthorityDto.class.getSimpleName() + "[", "]")
                .add("moduleName='" + moduleName + "'")
                .add("moduleMap=" + moduleMap)
                .toString();
    }
}
