package ru.aora.erp.domain.service;

import org.springframework.stereotype.Service;
import ru.aora.erp.domain.CrudGateway;
import ru.aora.erp.model.entity.business.Contract;

import javax.transaction.Transactional;
import java.util.List;

public class ContractService {
    private final CrudGateway<Contract, String> gateway;

    public ContractService(CrudGateway<Contract, String> gateway) {
        this.gateway = gateway;
    }

    public List<Contract> loadAll() {
        return gateway.loadAll();
    }

    public Contract update(Contract contract) {
        return gateway.update(contract).orElse(null);
    }

    public Contract create(Contract contract) {
        return gateway.create(contract);
    }

    public Contract delete(String id) {
        return gateway.delete(id).orElse(null);
    }
}



