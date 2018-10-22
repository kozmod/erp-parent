package ru.aora.erp.model.identifier.chane;

import java.util.List;
import java.util.Optional;

public interface UiChaneNode {
    List<UiChaneNode> next();
    Optional<String> mapping();
    String name();
    boolean isLast();
}
