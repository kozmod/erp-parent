package ru.aora.erp.model.identifier;

import java.util.List;
import java.util.Optional;

public interface ModuleIdentifier {
    Optional<String> moduleMapping();
    List<String> subMapping();
    String moduleIdentifier();
    int moduleId();
}
