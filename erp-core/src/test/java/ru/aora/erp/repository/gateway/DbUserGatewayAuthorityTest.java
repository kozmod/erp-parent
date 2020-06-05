package ru.aora.erp.repository.gateway;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.aora.erp.model.entity.db.Deactivatable;
import ru.aora.erp.model.entity.db.user.DbModule;
import ru.aora.erp.model.entity.db.user.DbModuleRolePair;
import ru.aora.erp.model.entity.db.user.DbRole;
import ru.aora.erp.repository.jpa.JpaModuleRepository;
import ru.aora.erp.repository.jpa.JpaModuleRolePairRepository;
import ru.aora.erp.repository.jpa.JpaRoleRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

public class DbUserGatewayAuthorityTest {

    @InjectMocks
    private DbModuleRolePairGateway moduleRolePairGateway;

    @Mock
    private JpaModuleRepository moduleRepo;

    @Mock
    private JpaRoleRepository roleRepo;

    @Mock
    private JpaModuleRolePairRepository moduleRolePairRepo;

    @Captor
    private ArgumentCaptor<DbModule> moduleArgumentCaptor;

    @Captor
    private ArgumentCaptor<DbRole> roleArgumentCaptor;

    private static final String ADD_ALL_ROLE_USER_ID = "ADD_ALL_ROLE_USER_ID";

    private static final String TARGET_ROLE_ID_A = "TARGET_ROLE_ID";
    private static final String TARGET_ROLE_NAME_A = "TARGET_ROLE_NAME";

    private static final String TARGET_MODULE_ID_A = "TARGET_MODULE_ID";
    private static final String TARGET_MODULE_NAME_A = "TARGET_MODULE_NAME";

    private static final String NEW_ROLE_ID_A = "NEW_ROLE_ID_A";
    private static final String NEW_ROLE_NAME_A = "NEW_ROLE_NAME_A";

    private static final String NEW_MODULE_ID_A = "NEW_MODULE_ID_A";
    private static final String NEW_MODULE_NAME_A = "NEW_MODULE_NAME_A";

    private static final Integer ENTITY_ACTIVE_STATUS = Deactivatable.ACTIVE_ENTITY_FLAG;
    private static final Integer ENTITY_INACTIVE_STATUS = Deactivatable.INACTIVE_ENTITY_FLAG;

    @Before
    public void initToActiveAndInactiveUserTest() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(moduleRepo.findAll()).thenReturn(List.of(
                new DbModule().setId(TARGET_MODULE_ID_A).setName(TARGET_MODULE_NAME_A)
        ));
        Mockito.when(moduleRepo.save(Mockito.any())).thenReturn(
                new DbModule().setId(NEW_MODULE_ID_A).setName(NEW_MODULE_NAME_A)
        );

        Mockito.when(roleRepo.findAll()).thenReturn(List.of(
                new DbRole().setId(TARGET_ROLE_ID_A).setName(TARGET_ROLE_NAME_A)
        ));
        Mockito.when(roleRepo.save(Mockito.any())).thenReturn(
                new DbRole().setId(NEW_ROLE_ID_A).setName(NEW_ROLE_NAME_A)
        );


        Mockito.when(moduleRolePairRepo.findAllActiveByUserId(ADD_ALL_ROLE_USER_ID)).thenReturn(
                Lists.emptyList()
        );


    }

    @Test
    public void should_add_module_and_role_name_to_source() {
        var source = List.of(
                new DbModuleRolePair().setRoleId(TARGET_ROLE_ID_A).setModuleId(TARGET_MODULE_ID_A)
        );
        var res = moduleRolePairGateway.addModuleRoleNameByIdFunction().apply(source);
        assertNotNull(res);
        assertThat(res, hasSize(1));
        var firstVal = first(res);
        assertNotNull(firstVal);
        assertEquals(TARGET_MODULE_ID_A, firstVal.getModuleId());
        assertEquals(TARGET_MODULE_NAME_A, firstVal.getModuleName());
        assertEquals(TARGET_ROLE_ID_A, firstVal.getRoleId());
        assertEquals(TARGET_ROLE_NAME_A, firstVal.getRoleName());
    }

    @Test
    public void should_create_role_module_and_set_ids() {
        var source = List.of(
                new DbModuleRolePair().setRoleName(NEW_ROLE_NAME_A).setModuleName(NEW_MODULE_NAME_A)
        );
        var res = moduleRolePairGateway.tryCreateAuthoritiesAndIds(source);
        verify(moduleRepo).save(moduleArgumentCaptor.capture());
        var captureModule = moduleArgumentCaptor.getValue();
        assertNotNull(captureModule);
        assertNull(captureModule.getId());
        assertNotNull(captureModule.getName());
        assertEquals(NEW_MODULE_NAME_A, captureModule.getName());

        verify(roleRepo).save(roleArgumentCaptor.capture());
        var captureRole = roleArgumentCaptor.getValue();
        assertNotNull(captureRole);
        assertNull(captureRole.getId());
        assertNotNull(captureRole.getName());
        assertEquals(NEW_ROLE_NAME_A, captureRole.getName());

        assertNotNull(res);
        assertThat(res, hasSize(1));
        var firstVal = first(res);
        assertNotNull(firstVal);
        assertNull(firstVal.getDeactivationDate());
        assertEquals(NEW_MODULE_ID_A, firstVal.getModuleId());
        assertEquals(NEW_MODULE_NAME_A, firstVal.getModuleName());
        assertEquals(NEW_ROLE_ID_A, firstVal.getRoleId());
        assertEquals(NEW_ROLE_NAME_A, firstVal.getRoleName());
    }


    @Test
    public void should_deactivate_all_source() {
        var source = List.of(
                new DbModuleRolePair().setDeactivationDate(null).setActiveStatus(ENTITY_ACTIVE_STATUS),
                new DbModuleRolePair().setDeactivationDate(null).setActiveStatus(ENTITY_ACTIVE_STATUS)
        );
        var res = moduleRolePairGateway.deactivateAll(source);
        assertNotNull(res);
        assertThat(res, hasSize(2));
        for (var resVal : res) {
            assertNotNull(resVal);
            assertNotNull(resVal.getDeactivationDate());
            assertNotNull(resVal.getActiveStatus());
            assertEquals(Deactivatable.INACTIVE_ENTITY_FLAG, resVal.getActiveStatus());

            assertNull(resVal.getModuleId());
            assertNull(resVal.getRoleId());
            assertNull(resVal.getModuleName());
            assertNull(resVal.getRoleName());
        }
    }

    /**
     * business key is `String key = ModuleName + RoleName`
     */
    @Test
    public void should_remove_value_from_source_with_same_target_business_key() {
        var soursActiveRole = "soursActiveRole";
        var soursActiveModule = "soursActiveModule";
        var soursActiveId = "soursActiveId_!#@^%$@";

        var targetActiveId = "targetActiveId_!#@^%$@";

        var source = List.of(
                new DbModuleRolePair()
                        .setId(soursActiveId)
                        .setModuleName(soursActiveModule)
                        .setRoleName(soursActiveRole)
                        .setActiveStatus(ENTITY_ACTIVE_STATUS)
        );
        var target = List.of(
                new DbModuleRolePair()
                        .setId(targetActiveId)
                        .setModuleName(soursActiveModule)
                        .setRoleName(soursActiveRole)
                        .setActiveStatus(ENTITY_ACTIVE_STATUS)
        );
        var res = moduleRolePairGateway.prepareToUpdate(source, target);
        assertNotNull(res);
        assertThat(res, hasSize(0));
    }

    /**
     * business key is `String key = ModuleName + RoleName`
     */
    @Test
    public void should_add_value_when_target_is_empty() {
        var soursActiveRole = "soursActiveRole";
        var soursActiveModule = "soursActiveModule";
        var soursActiveId = "soursActiveId_!#@^%$@";

        var source = List.of(
                new DbModuleRolePair()
                        .setId(soursActiveId)
                        .setModuleName(soursActiveModule)
                        .setRoleName(soursActiveRole)
                        .setActiveStatus(ENTITY_ACTIVE_STATUS)
        );
        var res = moduleRolePairGateway.prepareToUpdate(source, Collections.emptyList());
        assertNotNull(res);
        assertThat(res, hasSize(1));
        var firstVal = first(res);
        assertNotNull(firstVal);
        assertEquals(soursActiveRole, firstVal.getRoleName());
        assertEquals(soursActiveModule, firstVal.getModuleName());
        assertEquals(soursActiveId, firstVal.getId());
    }

    /**
     * business key is `String key = ModuleName + RoleName`
     */
    @Test
    public void should_add_to_result_active_source_value() {
        var soursActiveRole = "soursActiveRole";
        var soursActiveModule = "soursActiveModule";
        var soursActiveId = "soursActiveId_!#@^%$@";

        var targetActiveId = "targetActiveId_!#@^%$@";

        var source = List.of(
                new DbModuleRolePair()
                        .setId(soursActiveId)
                        .setModuleName(soursActiveModule)
                        .setRoleName(soursActiveRole)
                        .setActiveStatus(ENTITY_ACTIVE_STATUS)
        );
        var target = List.of(
                new DbModuleRolePair()
                        .setId(targetActiveId)
                        .setModuleName(soursActiveModule)
                        .setRoleName(soursActiveRole)
                        .setActiveStatus(ENTITY_INACTIVE_STATUS)
                        .setDeactivationDate(LocalDateTime.now())
        );
        var res = moduleRolePairGateway.prepareToUpdate(source, target);
        assertNotNull(res);
        assertThat(res, hasSize(1));
        var firstVal = first(res);
        assertNotNull(firstVal);
        assertEquals(soursActiveRole, firstVal.getRoleName());
        assertEquals(soursActiveModule, firstVal.getModuleName());
        assertEquals(soursActiveId, firstVal.getId());
        assertEquals(ENTITY_ACTIVE_STATUS, firstVal.getActiveStatus());
        assertNull(firstVal.getDeactivationDate());
    }

    /**
     * business key is `String key = ModuleName + RoleName`
     */
    @Test
    public void should_add_deactivated_to_result_when_source_value_is_not_present() {
        var targetActiveRole = "targetActiveRole";
        var targetActiveModule = "targetActiveModule";
        var targetActiveId = "targetActiveId_!#@^%$@";

        var target = List.of(
                new DbModuleRolePair()
                        .setId(targetActiveId)
                        .setModuleName(targetActiveModule)
                        .setRoleName(targetActiveRole)
                        .setActiveStatus(ENTITY_ACTIVE_STATUS)
                        .setDeactivationDate(null)
        );
        var res = moduleRolePairGateway.prepareToUpdate(Collections.emptyList(), target);
        assertNotNull(res);
        assertThat(res, hasSize(1));
        var firstVal = first(res);
        assertNotNull(firstVal);
        assertEquals(targetActiveRole, firstVal.getRoleName());
        assertEquals(targetActiveModule, firstVal.getModuleName());
        assertEquals(targetActiveId, firstVal.getId());
        assertEquals(ENTITY_INACTIVE_STATUS, firstVal.getActiveStatus());
        assertNotNull(firstVal.getDeactivationDate());
    }

    @Test
    public void should_remove_deactivated() {
        Collection<DbModuleRolePair> target = new ArrayList<>();
        target.add(null);
        target.add(
                new DbModuleRolePair()
                        .setActiveStatus(ENTITY_INACTIVE_STATUS)
                        .setDeactivationDate(LocalDateTime.now())
        );
        target.add(
                new DbModuleRolePair()
                        .setActiveStatus(ENTITY_INACTIVE_STATUS)
        );
        target.add(
                new DbModuleRolePair()
                        .setDeactivationDate(LocalDateTime.now())
        );
        var res = moduleRolePairGateway.removeAllDeactivated(target);
        assertNotNull(res);
        assertThat(res, hasSize(0));
    }

    private <T> T first(Collection<T> source) {
        for (T val : source) {
            return val;
        }
        throw new IllegalArgumentException("collection is empty: " + source);
    }

}
