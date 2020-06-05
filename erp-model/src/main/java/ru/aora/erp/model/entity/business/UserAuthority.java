package ru.aora.erp.model.entity.business;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

public final class UserAuthority implements GrantedAuthority {

    private final String moduleName;
    private final String roleName;

    public UserAuthority(String moduleName, String roleName) {
        this.moduleName = moduleName;
        this.roleName = roleName;
    }


    @Override
    public String getAuthority() {
        return moduleName + "_" + roleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthority that = (UserAuthority) o;
        return Objects.equals(moduleName, that.moduleName) &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleName, roleName);
    }

    @Override
    public String toString() {
        return "UserAuthority{" +
                "rootAuthority='" + moduleName + '\'' +
                ", subAuthority='" + roleName + '\'' +
                '}';
    }
}