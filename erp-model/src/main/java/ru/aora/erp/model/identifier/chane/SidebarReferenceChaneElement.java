package ru.aora.erp.model.identifier.chane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SidebarReferenceChaneElement implements ReferenceChaneElement {

    private String name;
    private String mapping;
    private List<ReferenceChaneElement> nextElements;

    public SidebarReferenceChaneElement(String name,String mapping) {
        this(name, mapping, null);
    }

    public SidebarReferenceChaneElement(String name, List<ReferenceChaneElement> nextElements) {
        this(name,null, nextElements);
    }

    public SidebarReferenceChaneElement(String name, String mapping, List<ReferenceChaneElement> nextElements) {
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
    public List<ReferenceChaneElement> next() {
        return Objects.nonNull(nextElements)
                ? nextElements
                : new ArrayList<>();
    }
}
