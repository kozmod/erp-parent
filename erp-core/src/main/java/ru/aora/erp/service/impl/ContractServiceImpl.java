package ru.aora.erp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aora.erp.model.entity.mapper.ContractMapper;
import ru.aora.erp.model.entity.business.Contract;
import ru.aora.erp.repository.crud.contract.DbContractRepository;
import ru.aora.erp.service.ContractService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {
    private final DbContractRepository contractRepository;
    private final ContractMapper contractMapper = ContractMapper.INSTANCE;

    @Autowired
    public ContractServiceImpl(DbContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract getByName(String name) throws UsernameNotFoundException {
        try {
            return contractRepository.findByName(name)
                    .map(contractMapper::toContract)
                    .orElseThrow(() -> new UsernameNotFoundException(name));
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("Contract not found by name: ", name), ex);
        }
    }

    public List<Contract> loadAll() {
        return contractRepository.findAll()
                .stream()
                .map(contractMapper::toContract)
                .collect(Collectors.toList());
    }

    public void update(Contract contract) {
        try {
            contractRepository.update(contractMapper.toDbContract(contract));
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("Contract not found by id"), ex);
        }
    }

    public void create(Contract contract) {
        try {
            contractRepository.create(contractMapper.toDbContract(contract));
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("Contract not found by id"), ex);
        }
    }

    public void delete(String contractId) {
        contractRepository.delete(contractId);
    }
}



