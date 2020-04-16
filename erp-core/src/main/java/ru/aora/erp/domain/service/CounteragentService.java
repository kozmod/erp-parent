package ru.aora.erp.domain.service;

import ru.aora.erp.domain.CrudGateway;
import ru.aora.erp.model.entity.business.Counteragent;
import ru.aora.erp.utils.common.CommonUtils;

import java.util.List;
import java.util.Objects;

public class CounteragentService {

    private final CrudGateway<Counteragent, String> gateway;

    public CounteragentService(CrudGateway<Counteragent, String> gateway) {
        this.gateway = gateway;
    }

    public List<Counteragent> loadAll() {
        return gateway.loadAll();
    }

    public Counteragent update(Counteragent counteragent) {
        Objects.requireNonNull(counteragent);
        return gateway.update(counteragent).orElse(null);
    }

    public Counteragent create(Counteragent counteragent) {
        Objects.requireNonNull(counteragent);
        return gateway.create(counteragent);
    }

    public Counteragent delete(String id) {
        CommonUtils.requiredNotBlank(id);
        return gateway.delete(id).orElse(null);
    }
}



