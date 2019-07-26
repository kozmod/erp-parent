package ru.aora.erp.service;

import ru.aora.erp.model.entity.business.Counteragent;

import java.util.List;

public interface CounteragentService {

    void delete(String id);

    Counteragent getByName(String name);

    void update(Counteragent counteragent);

    void create(Counteragent counteragent);

    List<Counteragent> loadAll();

}
