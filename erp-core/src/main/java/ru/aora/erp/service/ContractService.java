package ru.aora.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aora.erp.model.entity.db.DbContract;
import ru.aora.erp.model.entity.mapper.ContractMapper;
import ru.aora.erp.model.entity.business.Contract;
import ru.aora.erp.repository.jpa.DbContractRepository;
import ru.aora.erp.utils.common.CommonUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
@Transactional
public class ContractService {
    private final DbContractRepository contractRepository;
    private final ContractMapper contractMapper = ContractMapper.INSTANCE;

    @Autowired
    public ContractService(DbContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<Contract> loadAll() {
        return contractRepository.findAll()
                .stream()
                .map(contractMapper::toContract)
                .collect(Collectors.toList());
    }

    public Contract update(Contract contract) {
        DbContract savedContract = contractRepository.save(contractMapper.toDbContract(requireNonNull(contract)));
        return contractMapper.toContract(savedContract);
    }

    public Contract create(Contract contract) {
        DbContract savedContract = contractRepository.save(contractMapper.toDbContract(requireNonNull(contract)));
        return contractMapper.toContract(savedContract);
    }

    public String delete(String contractId) {
        CommonUtils.requiedNotBlank(contractId);
        contractRepository.deleteById(requireNonNull(contractId));
        return contractId;
    }
}



