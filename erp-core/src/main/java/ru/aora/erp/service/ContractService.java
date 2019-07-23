package ru.aora.erp.service;
import ru.aora.erp.model.entity.contract.Contract;


import java.util.List;

public interface ContractService {

    void delete(String id);
    Contract getByName(String name);
    void update(Contract contract);
    void create(Contract contract);

    List<Contract> loadAll();

}
