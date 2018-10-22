package ru.aora.erp.model.identifier.chane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SidebarChaneNode implements UiChaneNode {

    private String name;
    private String mapping;
    private List<UiChaneNode> nextElements;

    public SidebarChaneNode(String name, String mapping) {
        this(name, mapping, null);
    }

    public SidebarChaneNode(String name, List<UiChaneNode> nextElements) {
        this(name,null, nextElements);
    }

    public SidebarChaneNode(String name, String mapping, List<UiChaneNode> nextElements) {
        this.name = name;
        this.mapping = mapping;
        this.nextElements = nextElements;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Optional<String> mapping() {
        return Optional.ofNullable(mapping);
    }

    @Override
    public List<UiChaneNode> next() {
        return Objects.nonNull(nextElements)
                ? nextElements
                : new ArrayList<>();
    }

    @Override
    public boolean isLast() {
        return Objects.isNull(nextElements) || nextElements.isEmpty();
    }

}
