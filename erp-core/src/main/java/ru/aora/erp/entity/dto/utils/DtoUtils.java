package ru.aora.erp.entity.dto.utils;

import org.apache.commons.collections.CollectionUtils;
import ru.aora.erp.entity.dto.KsContractCounteragentDto;
import ru.aora.erp.model.entity.business.Contract;
import ru.aora.erp.model.entity.business.Counteragent;
import ru.aora.erp.model.entity.business.Ks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public final class DtoUtils {

    public static List<KsContractCounteragentDto> toKsContractCounteragentDtoList(List<Ks> ksList, List<Contract> contracts, List<Counteragent> counteragents) {
        final Map<String, Contract> contractById = hashMapByBusinessKey(contracts, Contract::getId);
        final Map<String, Counteragent> counteragentById = hashMapByBusinessKey(counteragents, Counteragent::getId);
        final List<KsContractCounteragentDto> resultList = new ArrayList<>(ksList.size());
        if (CollectionUtils.isNotEmpty(ksList)) {
            for (Ks ks : ksList) {
                if (ks != null) {
                    final KsContractCounteragentDto dto = asKsContractCounteragentDto(ks);
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

    static KsContractCounteragentDto asKsContractCounteragentDto(Ks ks) {
        return new KsContractCounteragentDto()
                .setKsId(ks.getId())
                .setKsDate(ks.getKsDate())
                .setKsNumber(ks.getKsNumber())
                .setGarantSum(ks.getGarantSum())
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
