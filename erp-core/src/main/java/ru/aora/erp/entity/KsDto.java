package ru.aora.erp.entity;

import ru.aora.erp.model.entity.business.Ks;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public final class KsDto {
    private List<Ks> kss;

    public KsDto() {
    }

    private KsDto(List<Ks> kss) { this.kss = kss; }

    public static KsDto of(List<Ks> kss){
        return new KsDto(kss);
    }

    public static KsDto of(Ks ... kss){
        return new KsDto(Arrays.asList(kss));
    }

    public List<Ks> getKs() {
        return kss;
    }

    public void addKs(Ks ks) {
        this.kss.add(ks);
    }

    public void setKs(List<Ks> kss) { this.kss = kss; }

    @Override
    public String toString() {
        return new StringJoiner(", ", KsDto.class.getSimpleName() + "[", "]")
                .add("kss=" + kss)
                .toString();
    }
}
