package ru.aora.erp.model.entity.business;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

public final class UserAuthority implements GrantedAuthority {

    private final String rootAuthority;
    private final String subAuthority;

    public UserAuthority(String rootAuthority, String subAuthority) {
        this.rootAuthority = rootAuthority;
        this.subAuthority = subAuthority;
    }


    @Override
    public String getAuthority() {
        return rootAuthority + "_" + subAuthority;
    }

    public String getRootAuthority() {
        return rootAuthority;
    }

    public String getSubAuthority() {
        return subAuthority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthority that = (UserAuthority) o;
        return Objects.equals(rootAuthority, that.rootAuthority) &&
                Objects.equals(subAuthority, that.subAuthority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rootAuthority, subAuthority);
    }

    @Override
    public String toString() {
        return "UserAuthority{" +
                "rootAuthority='" + rootAuthority + '\'' +
                ", subAuthority='" + subAuthority + '\'' +
                '}';
    }
}