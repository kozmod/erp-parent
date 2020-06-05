package ru.aora.erp.domain.service;

import ru.aora.erp.domain.CrudGateway;
import ru.aora.erp.domain.model.MsgServiceResult;
import ru.aora.erp.model.entity.business.Counteragent;

import java.util.List;

public class CounteragentService {

    private final CrudGateway<Counteragent, String> gateway;

    public CounteragentService(CrudGateway<Counteragent, String> gateway) {
        this.gateway = gateway;
    }

    public List<Counteragent> loadAll() {
        return gateway.loadAllActive();
    }

    public MsgServiceResult update(Counteragent counteragent) {
        return gateway.update(counteragent)
                .map(c -> MsgServiceResult.success("Counteragent updated"))
                .orElseGet(() -> MsgServiceResult.failed("Counteragent to update not found"));
    }

    public Counteragent create(Counteragent counteragent) {
        return gateway.create(counteragent);
    }

    public MsgServiceResult delete(String id) {
        return gateway.delete(id)
                .map(c -> MsgServiceResult.success("Counteragent deleted"))
                .orElseGet(() -> MsgServiceResult.failed("Counteragent to delete not found"));
    }


}



