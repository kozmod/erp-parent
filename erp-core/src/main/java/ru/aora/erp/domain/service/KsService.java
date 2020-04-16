package ru.aora.erp.domain.service;

import ru.aora.erp.domain.CrudGateway;
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
        return gateway.loadAll();
    }

    public Ks update(Ks ks) {
        Objects.requireNonNull(ks);
        return gateway.update(ks).orElse(null);
    }

    public Ks create(Ks ks) {
        Objects.requireNonNull(ks);
        return gateway.create(ks);
    }

    public Ks delete(String id) {
        CommonUtils.requiredNotBlank(id);
        return gateway.delete(id).orElse(null);
    }
}



