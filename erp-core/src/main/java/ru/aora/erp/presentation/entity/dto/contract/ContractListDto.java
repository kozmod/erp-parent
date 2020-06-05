package ru.aora.erp.presentation.entity.dto.contract;

import java.util.List;
import java.util.StringJoiner;

public final class ContractListDto {
    private List<ContractDto> contracts;

    private ContractListDto(List<ContractDto> contracts) {
        this.contracts = contracts;
    }

    public static ContractListDto of(List<ContractDto> contracts) {
        return new ContractListDto(contracts);
    }

    public List<ContractDto> getContracts() {
        return contracts;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ContractListDto.class.getSimpleName() + "[", "]")
                .add("contracts=" + contracts)
                .toString();
    }
}
