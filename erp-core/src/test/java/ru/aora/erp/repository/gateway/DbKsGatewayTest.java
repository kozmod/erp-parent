package ru.aora.erp.repository.gateway;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.model.entity.db.DbKs;
import ru.aora.erp.repository.jpa.JpaKsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DbKsGatewayTest {

    private DbKsGateway gateway;

    @Mock
    private JpaKsRepository repo;

    private static final String ACTIVE_KS_ID = "Some_active_id_2";
    private static final String INACTIVE_KS_ID = "Some_inactive_id_1";
    private static final String NOT_EXISTS_KS_ID = "Some_id_not_exists";

    @Before
    public void init() {
        final DbKs activeDbKs = new DbKs()
                .setId(ACTIVE_KS_ID)
                .setDeactivated(GatewayUtils.ACTIVE_ENTITY_FLAG);
        final DbKs inactiveDbKs = new DbKs()
                .setId(INACTIVE_KS_ID)
                .setDeactivated(GatewayUtils.INACTIVE_ENTITY_FLAG)
                .setDeactivationDate(LocalDateTime.now());

        MockitoAnnotations.initMocks(this);
        gateway = new DbKsGateway(repo);
        Mockito.when(repo.findAll()).thenReturn(List.of(activeDbKs, inactiveDbKs));
        Mockito.when(repo.getOne(ACTIVE_KS_ID)).thenReturn(activeDbKs);
        Mockito.when(repo.getOne(INACTIVE_KS_ID)).thenReturn(inactiveDbKs);
        Mockito.when(repo.findById(ACTIVE_KS_ID)).thenReturn(Optional.of(activeDbKs));
        Mockito.when(repo.findById(INACTIVE_KS_ID)).thenReturn(Optional.of(inactiveDbKs));
        Mockito.when(repo.save(Mockito.any())).thenReturn(activeDbKs);
    }

    @Test
    public void shouldFind_OnlyActiveKs() {
        List<Ks> res = gateway.loadAll();
        assertNotNull(res);
        assertEquals(1, res.size());
        assertEquals(ACTIVE_KS_ID, res.get(0).getId());
        assertEquals(GatewayUtils.ACTIVE_ENTITY_FLAG, res.get(0).getDeactivated());
        assertNull(res.get(0).getDeactivationDate());
    }

    @Test
    public void shouldCreateKs() {
        Ks res = gateway.create(activeKs());
        assertNotNull(res);
        assertEquals(ACTIVE_KS_ID, res.getId());
    }

    @Test
    public void shouldUpdate_ExistsActiveKs() {
        Optional<Ks> res = gateway.update(activeKs());
        assertTrue(res.isPresent());
        assertEquals(ACTIVE_KS_ID, res.get().getId());
    }

    @Test
    public void shouldNotUpdate_ExistsInactiveKs() {
        Optional<Ks> res = gateway.update(inactiveKs());
        assertFalse(res.isPresent());
    }

    @Test
    public void shouldNotUpdate_NotExistsKs() {
        Optional<Ks> res = gateway.update(notExistsKs());
        assertFalse(res.isPresent());
    }

    @Test
    public void shouldDelete_ExistsActiveKs() {
        Optional<Ks> res = gateway.delete(ACTIVE_KS_ID);
        assertTrue(res.isPresent());
        assertEquals(ACTIVE_KS_ID, res.get().getId());
        assertNotNull(res.get().getDeactivationDate());
        assertNotNull(res.get().getDeactivated());
        assertEquals(GatewayUtils.INACTIVE_ENTITY_FLAG, res.get().getDeactivated());
    }

    @Test
    public void shouldNotDelete_ExistsInactiveKs() {
        Optional<Ks> res = gateway.delete(INACTIVE_KS_ID);
        assertFalse(res.isPresent());
    }

    @Test
    public void shouldNotDelete_NotExistsKs() {
        Optional<Ks> res = gateway.delete(NOT_EXISTS_KS_ID);
        assertFalse(res.isPresent());
    }

    private static Ks activeKs() {
        return new Ks().setId(ACTIVE_KS_ID);
    }

    private static Ks inactiveKs() {
        return new Ks().setId(INACTIVE_KS_ID);
    }

    private static Ks notExistsKs() {
        return new Ks().setId(NOT_EXISTS_KS_ID);
    }
}
