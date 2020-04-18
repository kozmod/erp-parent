package ru.aora.erp.domain.service;

import ru.aora.erp.domain.CrudGateway;
import ru.aora.erp.domain.model.MsgServiceResult;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.utils.common.CommonUtils;

import java.util.List;
import java.util.Objects;

public class KsService {
    private final CrudGateway<Ks, String> gateway;

    public KsService(CrudGateway<Ks, String> gateway) {
        this.gateway = gateway;
    }

    public List<Ks> loadAll() {
        return gateway.loadAllActive();
    }

    public MsgServiceResult update(Ks ks) {
        Objects.requireNonNull(ks);
        return gateway.update(ks)
                .map(c -> MsgServiceResult.success("Ks updated"))
                .orElseGet(() -> MsgServiceResult.failed("Ks to update not found"));
    }

    public Ks create(Ks ks) {
        Objects.requireNonNull(ks);
        return gateway.create(ks);
    }

    public MsgServiceResult delete(String id) {
        CommonUtils.requiredNotBlank(id);
        return gateway.delete(id)
                .map(c -> MsgServiceResult.success("Ks deleted"))
                .orElseGet(() -> MsgServiceResult.failed("Ks to delete not found"));
    }
}



