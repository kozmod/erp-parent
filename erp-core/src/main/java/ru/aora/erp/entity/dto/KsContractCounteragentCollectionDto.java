package ru.aora.erp.entity.dto;

import java.util.Collection;
import java.util.StringJoiner;

public final class KsContractCounteragentCollectionDto {
    private Collection<KsContractCounteragentCollectionDto> ksContractConteragents;

    public Collection<KsContractCounteragentCollectionDto> getKsContractConteragents() {
        return ksContractConteragents;
    }

    public KsContractCounteragentCollectionDto setKsContractConteragents(Collection<KsContractCounteragentCollectionDto> ksContractConteragents) {
        this.ksContractConteragents = ksContractConteragents;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", KsContractCounteragentCollectionDto.class.getSimpleName() + "[", "]")
                .add("ksContractConteragents=" + ksContractConteragents)
                .toString();
    }
}
