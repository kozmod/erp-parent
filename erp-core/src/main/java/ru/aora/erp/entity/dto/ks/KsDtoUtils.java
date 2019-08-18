package ru.aora.erp.entity.dto.ks;

import org.apache.commons.collections.CollectionUtils;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.utils.common.CommonUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class KsDtoUtils {

    public static KsDto toKsDto(Ks ks) {
        return new KsDto()
                .setId(ks.getId())
                .setContractId(ks.getContractId())
                .setKsDate(ks.getKsDate())
                .setKsNumber(ks.getKsNumber())
                .setKsSum(ks.getKsSum())
                .setGarantDate(ks.getGarantDate())
                .setGarantSum(ks.getGarantSum())
                .setPaymentStatus(ks.getPaymentStatus())
                .setDaysToGarantDate(toDaysToGarantDate(ks.getGarantDate()));
    }

    public static Ks toKs(KsDto ks) {
        return new Ks()
                .setId(ks.getId())
                .setContractId(ks.getContractId())
                .setKsDate(ks.getKsDate())
                .setKsNumber(ks.getKsNumber())
                .setKsSum(ks.getKsSum())
                .setGarantDate(ks.getGarantDate())
                .setGarantSum(ks.getGarantSum())
                .setPaymentStatus(ks.getPaymentStatus());
    }

    public static List<KsDto> toKsDtoList(List<Ks> kss) {
        final List<KsDto> dtos = new ArrayList<>(kss.size());
        if (CollectionUtils.isNotEmpty(kss)) {
            for (Ks ks : kss) {
                dtos.add(toKsDto(ks));
            }
        }
        return dtos;
    }

    public static List<Ks> toKsList(List<KsDto> dtos) {
        final List<Ks> kss = new ArrayList<>(dtos.size());
        if (CollectionUtils.isNotEmpty(dtos)) {
            for (KsDto ksDto : dtos) {
                kss.add(toKs(ksDto));
            }
        }
        return kss;
    }

    private static Long toDaysToGarantDate(LocalDate date) {
        return date != null
                ? CommonUtils.daysToCurrentDate(date)
                : null;
    }
}
