package ru.aora.erp.component;

import org.springframework.stereotype.Component;
import ru.aora.erp.model.identifier.SidebarModuleIdentifier;
import ru.aora.erp.model.identifier.chane.ReferenceChaneElement;
import ru.aora.erp.model.identifier.chane.SidebarReferenceChaneElement;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class TestIdentifierSidebar implements SidebarModuleIdentifier {

    private ReferenceChaneElement firstReferenceChaneElement;

    @PostConstruct
    private void init() {
        final String PARENT_NAME = "TEST_MODULE";
        final String PARENT_CHILD_NAME = "TEST_PARENT_CHILD";
        final String PARENT_CHILD_CHILD_NAME = "TEST_PARENT_CHILD_CHILD";
        final String CHILD_MAPPING = "/child-mapping";


        this.firstReferenceChaneElement = new SidebarReferenceChaneElement(
                PARENT_NAME,
                Collections.singletonList(
                        new SidebarReferenceChaneElement(
                                PARENT_CHILD_NAME,
                                Collections.singletonList(
                                        new SidebarReferenceChaneElement(
                                                PARENT_CHILD_NAME,
                                                CHILD_MAPPING
                                        )

                                )
                        )
                )
        );
    }

    @Override
    public ReferenceChaneElement firstReferenceChaneElement() {
        return firstReferenceChaneElement;
    }

    @Override
    public String moduleIdentifier() {
        return TestIdentifierSidebar.class.toString();
    }

}
