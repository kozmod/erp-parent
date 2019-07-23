package ru.aora.erp.entity;

import ru.aora.erp.model.entity.contract.Contract;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public final class ContractDto {
    private List<Contract> contracts;

    public ContractDto() {
    }

    private ContractDto(List<Contract> contracts) { this.contracts = contracts; }

    public static ContractDto of(List<Contract> contracts){
        return new ContractDto(contracts);
    }

    public static ContractDto of(Contract ... contracts){
        return new ContractDto(Arrays.asList(contracts));
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void addContracts(Contract contract) {
        this.contracts.add(contract);
    }

    public void setContracts(List<Contract> contracts) { this.contracts = contracts; }

    @Override
    public String toString() {
        return new StringJoiner(", ", ContractDto.class.getSimpleName() + "[", "]")
                .add("contracts=" + contracts)
                .toString();
    }
}
