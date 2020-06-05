package ru.aora.erp.repository.gateway;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.model.entity.db.user.DbUser;
import ru.aora.erp.repository.jpa.JpaUserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.aora.erp.model.entity.db.Deactivatable.ACTIVE_ENTITY_FLAG;
import static ru.aora.erp.model.entity.db.Deactivatable.INACTIVE_ENTITY_FLAG;

public class DbUserGatewayTest {

    @InjectMocks
    private DbUserGateway gateway;

    @Mock
    private DbModuleRolePairGateway moduleRolePairGateway;

    @Mock
    private JpaUserRepository userRepo;

    private static final String ACTIVE_ID = "Some_active_id_2";
    private static final String ACTIVE_NAME = "Some_active_NAME";
    private static final String INACTIVE_ID = "Some_inactive_id_1";
    private static final String INACTIVE_NAME = "Some_inactive_NAME";
    private static final String NOT_EXISTS_NAME = "Some_NAME_not_exists";

    @Before
    public void initToActiveAndInactiveUserTest() {
        final DbUser active = new DbUser()
                .setId(ACTIVE_ID)
                .setUsername(ACTIVE_NAME)
                .setActiveStatus(ACTIVE_ENTITY_FLAG);
        final DbUser inactive = new DbUser()
                .setId(INACTIVE_ID)
                .setUsername(INACTIVE_NAME)
                .setActiveStatus(INACTIVE_ENTITY_FLAG)
                .setDeactivationDate(LocalDateTime.now());

        MockitoAnnotations.initMocks(this);
        Mockito.when(userRepo.findByName(ACTIVE_NAME)).thenReturn(List.of(active, inactive));
        Mockito.when(userRepo.findActiveByName(ACTIVE_NAME)).thenReturn(Optional.of(active));
        Mockito.when(userRepo.findActiveByName(INACTIVE_NAME)).thenReturn(Optional.empty());
        Mockito.when(userRepo.findActiveByName(NOT_EXISTS_NAME)).thenReturn(Optional.empty());
        Mockito.when(userRepo.findAll()).thenReturn(List.of(active, inactive));
        Mockito.when(userRepo.findById(ACTIVE_ID)).thenReturn(Optional.of(active));
        Mockito.when(userRepo.findById(INACTIVE_ID)).thenReturn(Optional.of(inactive));
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(active);
        Mockito.when(moduleRolePairGateway.addModuleRoleNameByIdFunction()).thenReturn(c -> c);


    }

    @Test
    public void shouldFind_OnlyActive() {
        List<User> res = gateway.loadAll();
        assertNotNull(res);
        assertEquals(1, res.size());
        assertEquals(ACTIVE_ID, res.get(0).getId());
        assertEquals(ACTIVE_ENTITY_FLAG, res.get(0).getActiveStatus());
        assertNull(res.get(0).getDeactivationDate());
    }

    @Test
    public void shouldCreate() {
        User res = gateway.create(notExists());
        assertNotNull(res);
        assertEquals(ACTIVE_ID, res.getId());
        assertEquals(ACTIVE_NAME, res.getUsername());
    }

    @Test
    public void shouldUpdate_ExistsActive() {
        Optional<User> res = gateway.update(active());
        res.ifPresentOrElse(
                u -> {
                    assertEquals(ACTIVE_ID, u.getId());
                    assertEquals(ACTIVE_NAME, u.getUsername());
                },
                Assertions::fail
        );
    }

    @Test
    public void shouldNotUpdate_ExistsInactive() {
        Optional<User> res = gateway.update(inactive());
        assertFalse(res.isPresent());
    }

    @Test
    public void shouldNotUpdate_NotExists() {
        Optional<User> res = gateway.update(notExists());
        assertFalse(res.isPresent());
    }

    @Test
    public void shouldDelete_ExistsActive() {
        Optional<User> res = gateway.delete(ACTIVE_NAME);
        assertTrue(res.isPresent());
        assertEquals(ACTIVE_ID, res.get().getId());
        assertEquals(ACTIVE_NAME, res.get().getUsername());
        assertNotNull(res.get().getDeactivationDate());
        assertNotNull(res.get().getActiveStatus());
        assertEquals(INACTIVE_ENTITY_FLAG, res.get().getActiveStatus());
    }

    @Test
    public void shouldNotDelete_ExistsInactive() {
        Optional<User> res = gateway.delete(INACTIVE_NAME);
        assertFalse(res.isPresent());
    }

    @Test
    public void shouldNotDelete_NotExists() {
        Optional<User> res = gateway.delete(NOT_EXISTS_NAME);
        assertFalse(res.isPresent());
    }

    private static User active() {
        return new User().setUsername(ACTIVE_NAME);
    }

    private static User inactive() {
        return new User().setUsername(INACTIVE_NAME);
    }

    private static User notExists() {
        return new User().setUsername(NOT_EXISTS_NAME);
    }
}
