package ru.aora.erp.repository.gateway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.aora.erp.domain.CrudGateway;
import ru.aora.erp.model.entity.business.Contract;
import ru.aora.erp.model.entity.db.DbContract;
import ru.aora.erp.model.entity.mapper.ContractMapper;
import ru.aora.erp.utils.common.CommonUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.aora.erp.repository.gateway.GatewayUtils.ACTIVE_ENTITY_FLAG;
import static ru.aora.erp.repository.gateway.GatewayUtils.INACTIVE_ENTITY_FLAG;

@Service
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
                .filter(this::isActive)
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
                .filter(this::isActive)
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
                .filter(this::isActive)
                .map(this::setDeactivated)
                .map(repository::save)
                .map(mapper::toContract)
                .or(Optional::empty);
    }

    private DbContract setDeactivated(DbContract contract) {
        return Objects.requireNonNull(contract)
                .setDeactivated(INACTIVE_ENTITY_FLAG)
                .setDeactivationDate(LocalDateTime.now());
    }

    private boolean isActive(DbContract contract) {
        return ACTIVE_ENTITY_FLAG.equals(contract.getDeactivated())
                && contract.getDeactivationDate() == null;
    }

}
