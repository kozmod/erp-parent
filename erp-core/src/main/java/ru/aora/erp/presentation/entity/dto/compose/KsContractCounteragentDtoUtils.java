package ru.aora.erp.presentation.entity.dto.compose;

import org.apache.commons.collections.CollectionUtils;
import ru.aora.erp.model.entity.business.Contract;
import ru.aora.erp.model.entity.business.Counteragent;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.utils.common.CommonUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public final class KsContractCounteragentDtoUtils {

    static final Supplier<Comparator<KsContractCounteragentDto>> GARANT_DATE_NATURAL_ORDER_COMPARATOR = () -> Comparator.nullsLast(Comparator.comparing(
            KsContractCounteragentDto::getGarantDate,
            Comparator.nullsLast(Comparator.naturalOrder())
    ));

    public static List<KsContractCounteragentDto> toKsContractCounteragentDtoList(List<Ks> ksList, List<Contract> contracts, List<Counteragent> counteragents) {
        final Map<String, Contract> contractById = hashMapByBusinessKey(contracts, Contract::getId);
        final Map<String, Counteragent> counteragentById = hashMapByBusinessKey(counteragents, Counteragent::getId);
        BigDecimal addBigDecimals = new BigDecimal(0);
        final List<KsContractCounteragentDto> resultList = new ArrayList<>(ksList.size());
        if (CollectionUtils.isNotEmpty(ksList)) {
            for (Ks ks : ksList) {
                if (ks != null && !ks.getPaymentStatus()) {
                    final KsContractCounteragentDto dto = updateDaysToGarantDate(
                            asKsContractCounteragentDto(ks)
                    );
                    final Contract contract = contractById.get(ks.getContractId());
                    if (contract != null) {
                        dto.setContractNumber(contract.getContractNumber());
                        dto.setConteragentId(contract.getCounteragentId());
                        final Counteragent counteragent = counteragentById.get(contract.getCounteragentId());
                        if (counteragent != null) {
                            dto.setConteragentName(counteragent.getCounteragentName());
                        }
                    }
                    resultList.add(dto);
                }
            }
        }
        return resultList;
    }

    static KsContractCounteragentDto updateDaysToGarantDate(KsContractCounteragentDto dto) {
        if (dto != null) {
            dto.setDaysToGarantDate(CommonUtils.daysToCurrentDate(dto.getGarantDate()));
        }
        return dto;
    }

    public static List<KsContractCounteragentDto> sortByGarantDateNaturalOrder(List<KsContractCounteragentDto> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            list.sort(GARANT_DATE_NATURAL_ORDER_COMPARATOR.get());
        }
        return list;
    }

    static KsContractCounteragentDto asKsContractCounteragentDto(Ks ks) {
        return new KsContractCounteragentDto()
                .setKsId(ks.getId())
                .setGarantDate(ks.getGarantDate())
                .setKsNumber(ks.getKsNumber())
                .setGarantSum(ks.getGarantSum())
                .setKsStatus(ks.getPaymentStatus())
                .setContractId(ks.getContractId());
    }

    static <K, V> Map<K, V> hashMapByBusinessKey(List<V> list, Function<V, K> keyFunc) {
        requireNonNull(keyFunc);
        final Map<K, V> map = new HashMap<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (V entity : list) {
                if (entity != null) {
                    final K key = keyFunc.apply(entity);
                    if (key != null) {
                        map.put(keyFunc.apply(entity), entity);
                    }
                }
            }
        }
        return map;
    }
}
