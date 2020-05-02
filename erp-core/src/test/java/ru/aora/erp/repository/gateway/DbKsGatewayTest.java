package ru.aora.erp.repository.gateway;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.model.entity.db.DbKs;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.aora.erp.model.entity.db.DbConstant.ACTIVE_ENTITY_FLAG;
import static ru.aora.erp.model.entity.db.DbConstant.INACTIVE_ENTITY_FLAG;

public class DbKsGatewayTest {

    @InjectMocks
    private DbKsGateway gateway;

    @Mock
    private JpaRepository<DbKs, String> repo;

    private static final String ACTIVE_ID = "Some_active_id_2";
    private static final String INACTIVE_ID = "Some_inactive_id_1";
    private static final String NOT_EXISTS_ID = "Some_id_not_exists";

    @Before
    public void init() {
        final DbKs active = new DbKs()
                .setId(ACTIVE_ID)
                .setDeactivated(ACTIVE_ENTITY_FLAG);
        final DbKs inactive = new DbKs()
                .setId(INACTIVE_ID)
                .setDeactivated(INACTIVE_ENTITY_FLAG)
                .setDeactivationDate(LocalDateTime.now());

        MockitoAnnotations.initMocks(this);
        Mockito.when(repo.findAll()).thenReturn(List.of(active, inactive));
        Mockito.when(repo.getOne(ACTIVE_ID)).thenReturn(active);
        Mockito.when(repo.getOne(INACTIVE_ID)).thenReturn(inactive);
        Mockito.when(repo.findById(ACTIVE_ID)).thenReturn(Optional.of(active));
        Mockito.when(repo.findById(INACTIVE_ID)).thenReturn(Optional.of(inactive));
        Mockito.when(repo.save(Mockito.any())).thenReturn(active);
    }

    @Test
    public void shouldFind_OnlyActive() {
        List<Ks> res = gateway.loadAllActive();
        assertNotNull(res);
        assertEquals(1, res.size());
        assertEquals(ACTIVE_ID, res.get(0).getId());
        assertEquals(ACTIVE_ENTITY_FLAG, res.get(0).getDeactivated());
        assertNull(res.get(0).getDeactivationDate());
    }

    @Test
    public void shouldCreate() {
        Ks res = gateway.create(active());
        assertNotNull(res);
        assertEquals(ACTIVE_ID, res.getId());
    }

    @Test
    public void shouldUpdate_ExistsActive() {
        Optional<Ks> res = gateway.update(active());
        res.ifPresentOrElse(
                r -> assertEquals(ACTIVE_ID, r.getId()),
                Assert::fail
        );
    }

    @Test
    public void shouldNotUpdate_ExistsInactive() {
        Optional<Ks> res = gateway.update(inactive());
        assertFalse(res.isPresent());
    }

    @Test
    public void shouldNotUpdate_NotExists() {
        Optional<Ks> res = gateway.update(notExists());
        assertFalse(res.isPresent());
    }

    @Test
    public void shouldDelete_ExistsActive() {
        Optional<Ks> res = gateway.delete(ACTIVE_ID);
        assertTrue(res.isPresent());
        assertEquals(ACTIVE_ID, res.get().getId());
        assertNotNull(res.get().getDeactivationDate());
        assertNotNull(res.get().getDeactivated());
        assertEquals(INACTIVE_ENTITY_FLAG, res.get().getDeactivated());
    }

    @Test
    public void shouldNotDelete_ExistsInactive() {
        Optional<Ks> res = gateway.delete(INACTIVE_ID);
        assertFalse(res.isPresent());
    }

    @Test
    public void shouldNotDelete_NotExists() {
        Optional<Ks> res = gateway.delete(NOT_EXISTS_ID);
        assertFalse(res.isPresent());
    }

    private static Ks active() {
        return new Ks().setId(ACTIVE_ID);
    }

    private static Ks inactive() {
        return new Ks().setId(INACTIVE_ID);
    }

    private static Ks notExists() {
        return new Ks().setId(NOT_EXISTS_ID);
    }
}
