package ru.aora.erp.model.entity.converter;

import ru.aora.erp.model.entity.contract.Contract;
import ru.aora.erp.model.entity.db.DbContract;

import static java.util.Objects.requireNonNull;

public class ContractConverter {

    public Contract convert(DbContract dbContract) {
        requireNonNull(dbContract, "DbContract should not be null");
        return new Contract()
                .setId(dbContract.getId())
                .setCounteragentId(dbContract.getCounteragentId())
                .setContractType(dbContract.getContractType())
                .setContractDate(dbContract.getContractDate())
                .setContractNumber(dbContract.getContractNumber())
                .setContractSubject(dbContract.getContractSubject());
    }

    public DbContract convert(Contract contract) {
        requireNonNull(contract, "DbContract should not be null");
        return new DbContract()
                .setId(contract.getId())
                .setCounteragentId(contract.getCounteragentId())
                .setContractType(contract.getContractType())
                .setContractDate(contract.getContractDate())
                .setContractNumber(contract.getContractNumber())
                .setContractSubject(contract.getContractSubject());
    }
}