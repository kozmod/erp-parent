package ru.aora.erp.presentation.entity.dto.ks;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public final class KsListDto {
    private List<KsDto> kss;

    private KsListDto(List<KsDto> kss) {
        this.kss = kss;
    }

    public static KsListDto of(List<KsDto> kss) {
        return new KsListDto(kss);
    }

    public static KsListDto of(KsDto... kss) {
        return new KsListDto(Arrays.asList(kss));
    }

    public List<KsDto> getKsDto() {
        return kss;
    }

    public void setKsDto(List<KsDto> kss) {
        this.kss = kss;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", KsListDto.class.getSimpleName() + "[", "]")
                .add("kss=" + kss)
                .toString();
    }
}
