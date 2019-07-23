package ru.aora.erp.model.entity.converter;

import ru.aora.erp.model.entity.contract.Contract;
import ru.aora.erp.model.entity.db.DbContract;

import static java.util.Objects.requireNonNull;

public class ContractConverter {

    public ContractConverter() {

    }

    public Contract convert(DbContract dbContract) {
        requireNonNull(dbContract, "DbContract should not be null");
        final var contract = new Contract();
        contract.setId(dbContract.getId());
        contract.setCounteragentId(dbContract.getCounteragentId());
        contract.setContractType(dbContract.getContractType());
        contract.setContractDate(dbContract.getContractDate());
        contract.setContractNumber(dbContract.getContractNumber());
        contract.setContractSubject(dbContract.getContractSubject());

        return contract;
    }


    public DbContract convert(Contract contract) {
        requireNonNull(contract, "DbContract should not be null");
        final var dbContract = new DbContract();
        dbContract.setId(contract.getId());
        dbContract.setCounteragentId(contract.getCounteragentId());
        dbContract.setContractType(contract.getContractType());
        dbContract.setContractDate(contract.getContractDate());
        dbContract.setContractNumber(contract.getContractNumber());
        dbContract.setContractSubject(contract.getContractSubject());

        return dbContract;
    }




}
