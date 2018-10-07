package ru.aora.erp.model.identifier.chane;

import java.util.List;
import java.util.Optional;

public interface ReferenceChaneElement {
    List<ReferenceChaneElement> next();
    Optional<String> mapping();
    String name();
}
