package ru.aora.erp.component;

import org.springframework.stereotype.Component;
import ru.aora.erp.model.identifier.SidebarModuleIdentifier;
import ru.aora.erp.model.identifier.chane.SidebarChaneNode;
import ru.aora.erp.model.identifier.chane.UiChaneNode;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class TestIdentifierSidebar implements SidebarModuleIdentifier {

    private UiChaneNode firstUiChaneNode;

    @PostConstruct
    private void init() {
        final String PARENT_NAME = "TEST_MODULE";
        final String PARENT_CHILD_NAME = "TEST_PARENT_CHILD";
        final String PARENT_CHILD_CHILD_NAME = "TEST_PARENT_CHILD_CHILD";
        final String CHILD_MAPPING = "/xxx";


        this.firstUiChaneNode = new SidebarChaneNode(
                PARENT_NAME,
                Collections.singletonList(
                        new SidebarChaneNode(
                                PARENT_CHILD_NAME,
                                Collections.singletonList(
                                        new SidebarChaneNode(
                                                PARENT_CHILD_NAME,
                                                CHILD_MAPPING
                                        )

                                )
                        )
                )
        );
    }

    @Override
    public UiChaneNode firstReferenceChaneElement() {
        return firstUiChaneNode;
    }

    @Override
    public String moduleIdentifier() {
        return TestIdentifierSidebar.class.toString();
    }

}
