package ru.aora.erp.model.entity.db;

import java.util.Set;

public class DbModule {

    private int id;
    private String name;

    private Set<String> moduleRoles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getModuleRoles() {
        return moduleRoles;
    }

    public void setModuleRoles(Set<String> moduleRoles) {
        this.moduleRoles = moduleRoles;
    }

    @Override
    public String toString() {
        return "DbModule{"
                .concat("id=")
                .concat(Integer.toString(id))
                .concat(", name=")
                .concat(name)
                .concat(", moduleRoles=")
                .concat(moduleRoles.toString())
                .concat("}");
    }
}
