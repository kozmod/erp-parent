package ru.aora.erp.entity.dto.utils;

import org.junit.Test;
import ru.aora.erp.entity.dto.KsContractCounteragentDto;
import ru.aora.erp.model.entity.business.Contract;
import ru.aora.erp.model.entity.business.Counteragent;
import ru.aora.erp.model.entity.business.Ks;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public final class DtoUtilsTest {

    @Test
    public void shouldMapToKsContractCounteragentDto() {
        final Ks ks = new Ks()
                .setId("KS_ID_123")
                .setGarantSum(BigDecimal.TEN)
                .setKsDate("2019-1-1")
                .setKsNumber("KsNumber_123456")
                .setContractId("CONTRACT_ID_2414124512");
        final Contract contract = new Contract()
                .setId(ks.getContractId())
                .setContractNumber("CONTRACT_NUMBER_21445")
                .setCounteragentId("COUNTERAGENT_ID_14523552");
        final Counteragent counteragent = new Counteragent()
                .setId(contract.getCounteragentId())
                .setCounteragentName("COUNTERAGENT_NAME-AssfdaD");

        List<KsContractCounteragentDto> dtoList = DtoUtils.toKsContractCounteragentDtoList(
                List.of(ks),
                List.of(contract),
                List.of(counteragent)
        );
        assertNotNull(dtoList);
        assertEquals(1, dtoList.size());
        assertNotNull(dtoList.get(0));
        assertNotNull(dtoList.get(0).getKsId());
        assertNotNull(dtoList.get(0).getKsDate());
        assertNotNull(dtoList.get(0).getKsNumber());
        assertNotNull(dtoList.get(0).getGarantSum());
        assertNotNull(dtoList.get(0).getContractId());
        assertNotNull(dtoList.get(0).getContractNumber());
        assertNotNull(dtoList.get(0).getConteragentId());
        assertNotNull(dtoList.get(0).getConteragentName());

        assertEquals(ks.getId(), dtoList.get(0).getKsId());
        assertEquals(ks.getKsDate(), dtoList.get(0).getKsDate());
        assertEquals(ks.getKsNumber(), dtoList.get(0).getKsNumber());
        assertEquals(ks.getGarantSum(), dtoList.get(0).getGarantSum());
        assertEquals(ks.getContractId(), dtoList.get(0).getContractId());
        assertEquals(contract.getContractNumber(), dtoList.get(0).getContractNumber());
        assertEquals(contract.getCounteragentId(), dtoList.get(0).getConteragentId());
        assertEquals(counteragent.getCounteragentName(), dtoList.get(0).getConteragentName());
    }

    @Test
    public void shouldNotMapContractAndCounteragent_ToKsContractCounteragentDto_WhenContractIdIsNull() {
        final String SOME_CONTRACT_ID = " SOME_CONTRACT_ID ";
        final Ks ks = new Ks()
                .setId("KS_ID_123")
                .setGarantSum(BigDecimal.TEN)
                .setKsDate("2019-1-1")
                .setKsNumber("KsNumber_123456")
                .setContractId(null);
        final Contract contract = new Contract()
                .setId(SOME_CONTRACT_ID)
                .setContractNumber("CONTRACT_NUMBER_21445")
                .setCounteragentId("COUNTERAGENT_ID_14523552");
        final Counteragent counteragent = new Counteragent()
                .setId(contract.getCounteragentId())
                .setCounteragentName("COUNTERAGENT_NAME-AssfdaD");

        List<KsContractCounteragentDto> dtoList = DtoUtils.toKsContractCounteragentDtoList(
                List.of(ks),
                List.of(contract),
                List.of(counteragent)
        );
        assertNotNull(dtoList);
        assertEquals(1, dtoList.size());
        assertNotNull(dtoList.get(0));
        assertNotNull(dtoList.get(0).getKsId());
        assertNotNull(dtoList.get(0).getKsDate());
        assertNotNull(dtoList.get(0).getKsNumber());
        assertNotNull(dtoList.get(0).getGarantSum());
        assertNull(dtoList.get(0).getContractId());
        assertNull(dtoList.get(0).getContractNumber());
        assertNull(dtoList.get(0).getConteragentId());
        assertNull(dtoList.get(0).getConteragentName());

        assertEquals(ks.getId(), dtoList.get(0).getKsId());
        assertEquals(ks.getKsDate(), dtoList.get(0).getKsDate());
        assertEquals(ks.getKsNumber(), dtoList.get(0).getKsNumber());
        assertEquals(ks.getGarantSum(), dtoList.get(0).getGarantSum());
    }

    @Test
    public void shouldNotMapCounteragent_ToKsContractCounteragentDto_WhencounteragenttIdIsNull() {
        final String SOME_COUNTERAGENT_ID= " SOME_COUNTERAGENT_ID";
        final Ks ks = new Ks()
                .setId("KS_ID_123")
                .setGarantSum(BigDecimal.TEN)
                .setKsDate("2019-1-1")
                .setKsNumber("KsNumber_123456")
                .setContractId("CONTRACT_ID_111");
        final Contract contract = new Contract()
                .setId(ks.getContractId())
                .setContractNumber("CONTRACT_NUMBER_21445")
                .setCounteragentId(null);
        final Counteragent counteragent = new Counteragent()
                .setId(SOME_COUNTERAGENT_ID)
                .setCounteragentName("COUNTERAGENT_NAME-AssfdaD");

        List<KsContractCounteragentDto> dtoList = DtoUtils.toKsContractCounteragentDtoList(
                List.of(ks),
                List.of(contract),
                List.of(counteragent)
        );

        assertNotNull(dtoList);
        assertEquals(1, dtoList.size());
        assertNotNull(dtoList.get(0));
        assertNotNull(dtoList.get(0).getKsId());
        assertNotNull(dtoList.get(0).getKsDate());
        assertNotNull(dtoList.get(0).getKsNumber());
        assertNotNull(dtoList.get(0).getGarantSum());
        assertNotNull(dtoList.get(0).getContractId());
        assertNotNull(dtoList.get(0).getContractNumber());
        assertNull(dtoList.get(0).getConteragentId());
        assertNull(dtoList.get(0).getConteragentName());

        assertEquals(ks.getId(), dtoList.get(0).getKsId());
        assertEquals(ks.getKsDate(), dtoList.get(0).getKsDate());
        assertEquals(ks.getKsNumber(), dtoList.get(0).getKsNumber());
        assertEquals(ks.getGarantSum(), dtoList.get(0).getGarantSum());
        assertEquals(ks.getContractId(), dtoList.get(0).getContractId());
        assertEquals(contract.getContractNumber(), dtoList.get(0).getContractNumber());
    }
}
