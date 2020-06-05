package ru.aora.erp.domain.service;

import ru.aora.erp.domain.CrudGateway;
import ru.aora.erp.domain.model.MsgServiceResult;
import ru.aora.erp.model.entity.business.Contract;

import java.util.List;

public class ContractService {
    private final CrudGateway<Contract, String> gateway;

    public ContractService(CrudGateway<Contract, String> gateway) {
        this.gateway = gateway;
    }

    public List<Contract> loadAll() {
        return gateway.loadAllActive();
    }

    public MsgServiceResult update(Contract contract) {
        return gateway.update(contract)
                .map(c -> MsgServiceResult.success("Contract updated"))
                .orElseGet(() -> MsgServiceResult.failed("Contract to update not found"));
    }

    public Contract create(Contract contract) {
        return gateway.create(contract);
    }

    public MsgServiceResult delete(String id) {
        return gateway.delete(id)
                .map(c -> MsgServiceResult.success("Contract deleted"))
                .orElseGet(() -> MsgServiceResult.failed("Contract to delete not found"));
    }
}



