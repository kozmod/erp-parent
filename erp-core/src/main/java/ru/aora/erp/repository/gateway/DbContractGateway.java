package ru.aora.erp.repository.gateway;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aora.erp.domain.CrudGateway;
import ru.aora.erp.model.entity.business.Contract;
import ru.aora.erp.model.entity.db.DbContract;
import ru.aora.erp.model.entity.db.Deactivatable;
import ru.aora.erp.model.entity.mapper.ContractMapper;
import ru.aora.erp.utils.common.CommonUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.aora.erp.model.entity.db.Deactivatable.INACTIVE_ENTITY_FLAG;

@Transactional
public class DbContractGateway implements CrudGateway<Contract, String> {

    private final JpaRepository<DbContract, String> repository;
    private final ContractMapper mapper = ContractMapper.INSTANCE;

    public DbContractGateway(JpaRepository<DbContract, String> repository) {
        this.repository = repository;
    }

    @Override
    public List<Contract> loadAllActive() {
        return repository.findAll()
                .stream()
                .filter(Deactivatable::isActive)
                .map(mapper::toContract)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public Optional<Contract> getById(String string) {
        return Optional.ofNullable(repository.getOne(string))
                .map(mapper::toContract)
                .or(Optional::empty);
    }

    @Override
    public Contract create(Contract contract) {
        contract.setId(null);
        DbContract res = repository.save(mapper.toDbContract(contract));
        return mapper.toContract(res);
    }

    @Override
    public Optional<Contract> update(Contract contract) {
        Optional<DbContract> optionalTarget = repository.findById(contract.getId())
                .filter(Deactivatable::isActive)
                .map(this::setDeactivated);
        if (optionalTarget.isPresent()) {
            repository.save(optionalTarget.get());
            contract.setId(null);
            DbContract res = repository.save(mapper.toDbContract(contract));
            return Optional.ofNullable(mapper.toContract(res));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Contract> delete(String id) {
        CommonUtils.requiredNotBlank(id);
        return repository.findById(id)
                .filter(Deactivatable::isActive)
                .map(this::setDeactivated)
                .map(repository::save)
                .map(mapper::toContract)
                .or(Optional::empty);
    }

    private DbContract setDeactivated(DbContract contract) {
        return Objects.requireNonNull(contract)
                .setActiveStatus(INACTIVE_ENTITY_FLAG)
                .setDeactivationDate(LocalDateTime.now());
    }

}
