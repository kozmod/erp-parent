package ru.aora.erp.presentation.presenter;

import ru.aora.erp.presentation.entity.dto.sidebar.SidebarPresenter;
import ru.aora.erp.presentation.entity.dto.sidebar.SidebarChaneNodeDto;

import javax.annotation.PostConstruct;
import java.util.*;

import static ru.aora.erp.presentation.controller.CounteragentController.COUNTERAGENT_ROOT_MAPPING;

public final class GarantSidebarPresenter implements SidebarPresenter {

    private static final String CHILD_MAPPING = COUNTERAGENT_ROOT_MAPPING;

    private SidebarChaneNodeDto firstUiChaneNode;

    @PostConstruct
    private void init() {
        final var PARENT_NAME = "ГАРАНТИЙКА";
        final var PARENT_CHILD_NAME = "КОНТРАГЕНТЫ";
        final var PARENT_CHILD_CHILD_NAME = "ДОГОВОРЫ";
        final var PARENT_CHILD_CHILD_CHILD_NAME = "КС";
        this.firstUiChaneNode = new SidebarChaneNodeDto(
                PARENT_NAME,
                Collections.singletonList(
                        new SidebarChaneNodeDto(
                                PARENT_CHILD_NAME,
                                CHILD_MAPPING
                                //Collections.singletonList(
                                //        new SidebarChaneNode(
                                //                PARENT_CHILD_CHILD_NAME,
                                //                Collections.singletonList(
                                //                        new SidebarChaneNode(
                                //                                PARENT_CHILD_CHILD_CHILD_NAME,
                                //
                                //                        )
                                //                )
                                //        )

                                //)
                        )
                )
        );
    }

    @Override
    public SidebarChaneNodeDto rootElement() {
        return firstUiChaneNode;
    }

}
