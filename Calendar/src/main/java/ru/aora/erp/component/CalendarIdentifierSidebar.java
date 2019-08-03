package ru.aora.erp.component;

import org.springframework.stereotype.Component;
import ru.aora.erp.model.entity.business.IdAuthority;
import ru.aora.erp.model.identifier.SidebarModuleIdentifier;
import ru.aora.erp.model.identifier.chane.SidebarChaneNode;
import ru.aora.erp.model.identifier.chane.UiChaneNode;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class CalendarIdentifierSidebar implements SidebarModuleIdentifier {

    private final String CHILD_MAPPING = "/Garantsort";

    private UiChaneNode firstUiChaneNode;
    private Map<String, IdAuthority> mappingAuthorities;

    public CalendarIdentifierSidebar() {
        this.mappingAuthorities = new HashMap<>();
    }

    @PostConstruct
    private void init() {
        final String PARENT_NAME = "КАЛЕНДАРЬ";
        final String PARENT_CHILD_NAME = "РЕЙТИНГ ДАТ ВОЗВРАТА";
        final String PARENT_CHILD_CHILD_NAME = "ДОГОВОРЫ";
        final String PARENT_CHILD_CHILD_CHILD_NAME = "КС";

        prepareModuleAuthority();
        this.firstUiChaneNode = new SidebarChaneNode(
                PARENT_NAME,
                        Collections.singletonList(
                                new SidebarChaneNode(
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

    private void prepareModuleAuthority() {
        mappingAuthorities.put(CHILD_MAPPING, CalendarModuleAuthority.ADD);
        mappingAuthorities.put(CHILD_MAPPING, CalendarModuleAuthority.DELETE);
    }

    @Override
    public UiChaneNode firstReferenceChaneElement() {
        return firstUiChaneNode;
    }

    @Override
    public String moduleIdentifier() {
        return CalendarIdentifierSidebar.class.toString();
    }

    @Override
    public Set<IdAuthority> moduleAuthorities() {
        return new HashSet<>(Arrays.asList(CalendarModuleAuthority.values()));
    }

    @Override
    public Map<String, IdAuthority> moduleMapping() {
        return mappingAuthorities;
    }
}
