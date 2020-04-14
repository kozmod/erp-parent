package ru.aora.erp.domain;

import ru.aora.erp.model.entity.business.Ks;

import java.util.List;
import java.util.Optional;

public interface KsGateway {

    List<Ks> loadAll();

    Ks create(Ks ks);

    Optional<Ks> update(Ks ks);

    Optional<Ks> delete(String ks);

}

