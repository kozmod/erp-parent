package ru.aora.erp.presentation.entity.dto.sidebar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class SidebarChaneNodeDto {

    private final String name;
    private final String mapping;
    private final List<SidebarChaneNodeDto> nextElements;

    public SidebarChaneNodeDto(String name, String mapping) {
        this(name, mapping, null);
    }

    public SidebarChaneNodeDto(String name, List<SidebarChaneNodeDto> nextElements) {
        this(name, null, nextElements);
    }

    public SidebarChaneNodeDto(String name, String mapping, List<SidebarChaneNodeDto> nextElements) {
        this.name = name;
        this.mapping = mapping;
        this.nextElements = nextElements;
    }

    public String name() {
        return name;
    }

    public Optional<String> mapping() {
        return Optional.ofNullable(mapping);
    }

    public List<SidebarChaneNodeDto> next() {
        return Objects.nonNull(nextElements)
                ? nextElements
                : new ArrayList<>();
    }

    public boolean isLast() {
        return Objects.isNull(nextElements) || nextElements.isEmpty();
    }

    public String toString() {
        return "SidebarChaneNode{" +
                "name='" + name + '\'' +
                ", mapping='" + mapping + '\'' +
                ", nextElements=" + nextElements +
                '}';
    }
}
