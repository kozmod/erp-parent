package ru.aora.erp.presentation.presenter;

import ru.aora.erp.presentation.controller.TestController;
import ru.aora.erp.presentation.entity.dto.sidebar.SidebarChaneNodeDto;
import ru.aora.erp.presentation.entity.dto.sidebar.SidebarPresenter;

import javax.annotation.PostConstruct;

/**
 * Test Sidebar Presenter
 */
@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
public final class TestSidebarPresenter implements SidebarPresenter {

    private static final String CHILD_MAPPING = TestController.MAPPING;

    private SidebarChaneNodeDto firstUiChaneNode;

    @PostConstruct
    private void init() {
        final var PARENT_NAME = "Test";
        this.firstUiChaneNode = new SidebarChaneNodeDto(
                PARENT_NAME,
                CHILD_MAPPING
//                Collections.singletonList(
//                        new SidebarChaneNodeDto(
//                                "XXX",
//                                CHILD_MAPPING
//                        )
//                )
        );
    }

    @Override
    public SidebarChaneNodeDto rootElement() {
        return firstUiChaneNode;
    }

}
