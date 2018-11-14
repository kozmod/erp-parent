package ru.aora.erp.component;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import ru.aora.erp.model.entity.IdAuthority;
import ru.aora.erp.model.identifier.SidebarModuleIdentifier;
import ru.aora.erp.model.identifier.chane.SidebarChaneNode;
import ru.aora.erp.model.identifier.chane.UiChaneNode;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class TestIdentifierSidebar implements SidebarModuleIdentifier {

    private final String CHILD_MAPPING = "/xxx";

    private UiChaneNode firstUiChaneNode;
    private Map<String, IdAuthority> mappingAuthorities;

    public TestIdentifierSidebar() {
        this.mappingAuthorities = new HashMap<>();
    }

    @PostConstruct
    private void init() {
        final String PARENT_NAME = "TEST_MODULE";
        final String PARENT_CHILD_NAME = "TEST_PARENT_CHILD";
        final String PARENT_CHILD_CHILD_NAME = "TEST_PARENT_CHILD_CHILD";

        prepareModuleAuthority();
        this.firstUiChaneNode = new SidebarChaneNode(
                PARENT_NAME,
                Collections.singletonList(
                        new SidebarChaneNode(
                                PARENT_CHILD_NAME,
                                Collections.singletonList(
                                        new SidebarChaneNode(
                                                PARENT_CHILD_CHILD_NAME,
                                                CHILD_MAPPING
                                        )

                                )
                        )
                )
        );
    }

    private void prepareModuleAuthority() {
        mappingAuthorities.put(CHILD_MAPPING, TestModuleAuthority.ADD);
        mappingAuthorities.put(CHILD_MAPPING, TestModuleAuthority.DELETE);
    }

    @Override
    public UiChaneNode firstReferenceChaneElement() {
        return firstUiChaneNode;
    }

    @Override
    public String moduleIdentifier() {
        return TestIdentifierSidebar.class.toString();
    }

    @Override
    public Set<IdAuthority> moduleAuthorities() {
        return new HashSet<>(Arrays.asList(TestModuleAuthority.values()));
    }

    @Override
    public Map<String, IdAuthority> moduleMapping() {
        return mappingAuthorities;
    }
}
