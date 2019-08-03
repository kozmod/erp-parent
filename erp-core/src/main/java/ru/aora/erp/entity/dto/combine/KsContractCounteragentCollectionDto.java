package ru.aora.erp.entity.dto.combine;

import java.util.Collection;
import java.util.StringJoiner;

public final class KsContractCounteragentCollectionDto {
    private Collection<KsContractCounteragentDto> ksContractConteragents;

    public Collection<KsContractCounteragentDto> getKsContractConteragents() {
        return ksContractConteragents;
    }

    public KsContractCounteragentCollectionDto setKsContractConteragents(Collection<KsContractCounteragentDto> ksContractConteragents) {
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
