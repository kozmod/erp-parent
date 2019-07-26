package ru.aora.erp.service;


import ru.aora.erp.model.entity.business.Ks;

import java.util.List;

public interface KsService {

    void delete(String id);
    Ks getByName(String name);
    void update(Ks ks);
    void create(Ks ks);

    List<Ks> loadAll();

}
