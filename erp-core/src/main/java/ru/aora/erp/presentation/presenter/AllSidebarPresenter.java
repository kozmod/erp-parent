package ru.aora.erp.presentation.presenter;

import ru.aora.erp.presentation.entity.dto.sidebar.SidebarChaneNodeDto;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    //todo use in dashboard controller(sidebar filter by role)
    private List<SidebarChaneNodeDto> allNodesWithExistsMapping(Set<String> mappings) {
        return allNodesWithExistsMapping(
                allRootNodes(),
                Objects.requireNonNull(mappings)
        );
    }

    private List<SidebarChaneNodeDto> allNodesWithExistsMapping(List<SidebarChaneNodeDto> nodes, Set<String> mappings) {
        for (Iterator<SidebarChaneNodeDto> it = nodes.iterator(); it.hasNext(); ) {
            final SidebarChaneNodeDto current = it.next();
            if (!current.isLast()) {
                allNodesWithExistsMapping(current.next(), mappings);
            } else {
                if (current.next().isEmpty()) {
                    it.remove();
                }
                current.mapping()
                        .filter(mappings::contains)
                        .ifPresent(m -> it.remove());
            }
        }
        return nodes;
    }

    @Override
    public String toString() {
        return "AllSidebarPresenter{" +
                "allRootNodes=" + allRootNodes +
                '}';
    }
}
