package ru.aora.erp.domain.service;

import ru.aora.erp.domain.KsGateway;
import ru.aora.erp.model.entity.business.Ks;

import java.util.List;
import java.util.Objects;

public class KsService {
    private final KsGateway gateway;

    public KsService(KsGateway gateway) {
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
        Objects.requireNonNull(id);
        return gateway.delete(id).orElse(null);
    }
}



