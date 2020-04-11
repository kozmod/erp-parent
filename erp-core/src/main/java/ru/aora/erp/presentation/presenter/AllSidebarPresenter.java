package ru.aora.erp.presentation.presenter;

import ru.aora.erp.presentation.entity.dto.sidebar.SidebarChaneNodeDto;

import java.util.List;

public final class AllSidebarPresenter {

    private final List<SidebarChaneNodeDto> allRootNodes;

    public AllSidebarPresenter(List<SidebarChaneNodeDto> allRootNodes) {
        this.allRootNodes = allRootNodes;
    }

    public void addRootNode(SidebarChaneNodeDto node) {
        allRootNodes.add(node);
    }

    public List<SidebarChaneNodeDto> allRootNodes() {
        return List.copyOf(allRootNodes);
    }

    @Override
    public String toString() {
        return "AllSidebarPresenter{" +
                "allRootNodes=" + allRootNodes +
                '}';
    }
}
