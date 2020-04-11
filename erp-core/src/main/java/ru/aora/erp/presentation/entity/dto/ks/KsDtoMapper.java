package ru.aora.erp.presentation.entity.dto.ks;

import ru.aora.erp.presentation.entity.dto.utils.MapperUtils;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.utils.common.CommonUtils;

import java.time.LocalDate;
import java.util.List;

public final class KsDtoMapper {

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
        return MapperUtils.convert(kss, KsDtoMapper::toKsDto);
    }

    private static Long toDaysToGarantDate(LocalDate date) {
        return date != null
                ? CommonUtils.daysToCurrentDate(date)
                : null;
    }
}
