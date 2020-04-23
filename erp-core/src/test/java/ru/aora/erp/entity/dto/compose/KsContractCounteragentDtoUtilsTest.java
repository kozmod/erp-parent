package ru.aora.erp.entity.dto.compose;

import org.junit.Test;
import ru.aora.erp.model.entity.business.Contract;
import ru.aora.erp.model.entity.business.Counteragent;
import ru.aora.erp.model.entity.business.Ks;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public final class KsContractCounteragentDtoUtilsTest {

    @Test
    public void shouldMapToKsContractCounteragentDto() {
        final Ks ks = new Ks()
                .setId("KS_ID_123")
                .setGarantSum(BigDecimal.TEN)
                .setGarantDate(LocalDate.parse("2019-1-1"))
                .setKsNumber("KsNumber_123456")
                .setContractId("CONTRACT_ID_2414124512");
        final Contract contract = new Contract()
                .setId(ks.getContractId())
                .setContractNumber("CONTRACT_NUMBER_21445")
                .setCounteragentId("COUNTERAGENT_ID_14523552");
        final Counteragent counteragent = new Counteragent()
                .setId(contract.getCounteragentId())
                .setCounteragentName("COUNTERAGENT_NAME-AssfdaD");

        List<KsContractCounteragentDto> dtoList = KsContractCounteragentDtoUtils.toKsContractCounteragentDtoList(
                List.of(ks),
                List.of(contract),
                List.of(counteragent)
        );
        assertNotNull(dtoList);
        assertEquals(1, dtoList.size());
        assertNotNull(dtoList.get(0));
        assertNotNull(dtoList.get(0).getKsId());
        assertNotNull(dtoList.get(0).getGarantDate());
        assertNotNull(dtoList.get(0).getKsNumber());
        assertNotNull(dtoList.get(0).getGarantSum());
        assertNotNull(dtoList.get(0).getContractId());
        assertNotNull(dtoList.get(0).getContractNumber());
        assertNotNull(dtoList.get(0).getConteragentId());
        assertNotNull(dtoList.get(0).getConteragentName());

        assertEquals(ks.getId(), dtoList.get(0).getKsId());
        assertEquals(ks.getGarantDate(), dtoList.get(0).getGarantDate());
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
                .setGarantDate(LocalDate.parse("2019-1-1"))
                .setKsNumber("KsNumber_123456")
                .setContractId(null);
        final Contract contract = new Contract()
                .setId(SOME_CONTRACT_ID)
                .setContractNumber("CONTRACT_NUMBER_21445")
                .setCounteragentId("COUNTERAGENT_ID_14523552");
        final Counteragent counteragent = new Counteragent()
                .setId(contract.getCounteragentId())
                .setCounteragentName("COUNTERAGENT_NAME-AssfdaD");

        List<KsContractCounteragentDto> dtoList = KsContractCounteragentDtoUtils.toKsContractCounteragentDtoList(
                List.of(ks),
                List.of(contract),
                List.of(counteragent)
        );
        assertNotNull(dtoList);
        assertEquals(1, dtoList.size());
        assertNotNull(dtoList.get(0));
        assertNotNull(dtoList.get(0).getKsId());
        assertNotNull(dtoList.get(0).getGarantDate());
        assertNotNull(dtoList.get(0).getKsNumber());
        assertNotNull(dtoList.get(0).getGarantSum());
        assertNull(dtoList.get(0).getContractId());
        assertNull(dtoList.get(0).getContractNumber());
        assertNull(dtoList.get(0).getConteragentId());
        assertNull(dtoList.get(0).getConteragentName());

        assertEquals(ks.getId(), dtoList.get(0).getKsId());
        assertEquals(ks.getGarantDate(), dtoList.get(0).getGarantDate());
        assertEquals(ks.getKsNumber(), dtoList.get(0).getKsNumber());
        assertEquals(ks.getGarantSum(), dtoList.get(0).getGarantSum());
    }

    @Test
    public void shouldNotMapCounteragent_ToKsContractCounteragentDto_WhencounteragenttIdIsNull() {
        final String SOME_COUNTERAGENT_ID = " SOME_COUNTERAGENT_ID";
        final Ks ks = new Ks()
                .setId("KS_ID_123")
                .setGarantSum(BigDecimal.TEN)
                .setGarantDate(LocalDate.parse("2019-1-1"))
                .setKsNumber("KsNumber_123456")
                .setContractId("CONTRACT_ID_111");
        final Contract contract = new Contract()
                .setId(ks.getContractId())
                .setContractNumber("CONTRACT_NUMBER_21445")
                .setCounteragentId(null);
        final Counteragent counteragent = new Counteragent()
                .setId(SOME_COUNTERAGENT_ID)
                .setCounteragentName("COUNTERAGENT_NAME-AssfdaD");

        List<KsContractCounteragentDto> dtoList = KsContractCounteragentDtoUtils.toKsContractCounteragentDtoList(
                List.of(ks),
                List.of(contract),
                List.of(counteragent)
        );

        assertNotNull(dtoList);
        assertEquals(1, dtoList.size());
        assertNotNull(dtoList.get(0));
        assertNotNull(dtoList.get(0).getKsId());
        assertNotNull(dtoList.get(0).getGarantDate());
        assertNotNull(dtoList.get(0).getKsNumber());
        assertNotNull(dtoList.get(0).getGarantSum());
        assertNotNull(dtoList.get(0).getContractId());
        assertNotNull(dtoList.get(0).getContractNumber());
        assertNull(dtoList.get(0).getConteragentId());
        assertNull(dtoList.get(0).getConteragentName());

        assertEquals(ks.getId(), dtoList.get(0).getKsId());
        assertEquals(ks.getGarantDate(), dtoList.get(0).getGarantDate());
        assertEquals(ks.getKsNumber(), dtoList.get(0).getKsNumber());
        assertEquals(ks.getGarantSum(), dtoList.get(0).getGarantSum());
        assertEquals(ks.getContractId(), dtoList.get(0).getContractId());
        assertEquals(contract.getContractNumber(), dtoList.get(0).getContractNumber());
    }

    @Test
    public void shouldSortByGarantDate_NaturalOrder() {
        final LocalDate NOW = LocalDate.now();
        final LocalDate PAST = NOW.minusDays(111);
        final String KS_ID_NOW = "123";
        final String KS_ID_PAST = "456";
        final String KS_ID_NULL_DATE = "789";

        final List<KsContractCounteragentDto> dtos = Arrays.asList(
                new KsContractCounteragentDto().setKsId(KS_ID_PAST).setGarantDate(PAST),
                new KsContractCounteragentDto().setKsId(KS_ID_NULL_DATE).setGarantDate(null),
                new KsContractCounteragentDto().setKsId(KS_ID_NOW).setGarantDate(NOW),
                null
        );

        Collections.shuffle(dtos);
        dtos.sort(KsContractCounteragentDtoUtils.GARANT_DATE_NATURAL_ORDER_COMPARATOR.get());

        assertEquals(4, dtos.size());
        assertEquals(KS_ID_PAST, dtos.get(0).getKsId());
        assertEquals(KS_ID_NOW, dtos.get(1).getKsId());
        assertEquals(KS_ID_NULL_DATE, dtos.get(2).getKsId());
        assertNull(KS_ID_NULL_DATE, dtos.get(3));
    }
}
