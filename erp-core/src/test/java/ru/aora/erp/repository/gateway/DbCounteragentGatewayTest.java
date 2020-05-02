package ru.aora.erp.repository.gateway;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.aora.erp.model.entity.business.Counteragent;
import ru.aora.erp.model.entity.db.DbCounteragent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.aora.erp.model.entity.db.DbConstant.ACTIVE_ENTITY_FLAG;
import static ru.aora.erp.model.entity.db.DbConstant.INACTIVE_ENTITY_FLAG;

public class DbCounteragentGatewayTest {

    @InjectMocks
    private DbCounteragentGateway gateway;

    @Mock
    private JpaRepository<DbCounteragent, String> repo;

    private static final String ACTIVE_ID = "Some_active_id_2";
    private static final String INACTIVE_ID = "Some_inactive_id_1";
    private static final String NOT_EXISTS_ID = "Some_id_not_exists";

    @Before
    public void init() {
        final DbCounteragent active = new DbCounteragent()
                .setId(ACTIVE_ID)
                .setDeactivated(ACTIVE_ENTITY_FLAG);
        final DbCounteragent inactive = new DbCounteragent()
                .setId(INACTIVE_ID)
                .setDeactivated(INACTIVE_ENTITY_FLAG)
                .setDeactivationDate(LocalDateTime.now());

        MockitoAnnotations.initMocks(this);
        gateway = new DbCounteragentGateway(repo);
        Mockito.when(repo.findAll()).thenReturn(List.of(active, inactive));
        Mockito.when(repo.getOne(ACTIVE_ID)).thenReturn(active);
        Mockito.when(repo.getOne(INACTIVE_ID)).thenReturn(inactive);
        Mockito.when(repo.findById(ACTIVE_ID)).thenReturn(Optional.of(active));
        Mockito.when(repo.findById(INACTIVE_ID)).thenReturn(Optional.of(inactive));
        Mockito.when(repo.save(Mockito.any())).thenReturn(active);
    }

    @Test
    public void shouldFind_OnlyActive() {
        List<Counteragent> res = gateway.loadAllActive();
        assertNotNull(res);
        assertEquals(1, res.size());
        assertEquals(ACTIVE_ID, res.get(0).getId());
        assertEquals(ACTIVE_ENTITY_FLAG, res.get(0).getDeactivated());
        assertNull(res.get(0).getDeactivationDate());
    }

    @Test
    public void shouldCreate() {
        Counteragent res = gateway.create(active());
        assertNotNull(res);
        assertEquals(ACTIVE_ID, res.getId());
    }

    @Test
    public void shouldUpdate_ExistsActive() {
        Optional<Counteragent> res = gateway.update(active());
        res.ifPresentOrElse(
                r -> assertEquals(ACTIVE_ID, r.getId()),
                Assert::fail
        );
    }

    @Test
    public void shouldNotUpdate_ExistsInactive() {
        Optional<Counteragent> res = gateway.update(inactive());
        assertFalse(res.isPresent());
    }

    @Test
    public void shouldNotUpdate_NotExists() {
        Optional<Counteragent> res = gateway.update(notExists());
        assertFalse(res.isPresent());
    }

    @Test
    public void shouldDelete_ExistsActive() {
        Optional<Counteragent> res = gateway.delete(ACTIVE_ID);
        assertTrue(res.isPresent());
        assertEquals(ACTIVE_ID, res.get().getId());
        assertNotNull(res.get().getDeactivationDate());
        assertNotNull(res.get().getDeactivated());
        assertEquals(INACTIVE_ENTITY_FLAG, res.get().getDeactivated());
    }

    @Test
    public void shouldNotDelete_ExistsInactive() {
        Optional<Counteragent> res = gateway.delete(INACTIVE_ID);
        assertFalse(res.isPresent());
    }

    @Test
    public void shouldNotDelete_NotExists() {
        Optional<Counteragent> res = gateway.delete(NOT_EXISTS_ID);
        assertFalse(res.isPresent());
    }

    private static Counteragent active() {
        return new Counteragent().setId(ACTIVE_ID);
    }

    private static Counteragent inactive() {
        return new Counteragent().setId(INACTIVE_ID);
    }

    private static Counteragent notExists() {
        return new Counteragent().setId(NOT_EXISTS_ID);
    }
}
