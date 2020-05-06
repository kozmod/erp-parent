package ru.aora.erp.repository.gateway;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aora.erp.domain.CrudGateway;
import ru.aora.erp.model.entity.business.Counteragent;
import ru.aora.erp.model.entity.db.DbCounteragent;
import ru.aora.erp.model.entity.mapper.CounteragentMapper;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.aora.erp.model.entity.db.DbConstant.ACTIVE_ENTITY_FLAG;
import static ru.aora.erp.model.entity.db.DbConstant.INACTIVE_ENTITY_FLAG;

@Transactional
public class DbCounteragentGateway implements CrudGateway<Counteragent, String> {

    private final JpaRepository<DbCounteragent, String> repository;
    private final CounteragentMapper mapper = CounteragentMapper.INSTANCE;

    public DbCounteragentGateway(JpaRepository<DbCounteragent, String> repository) {
        this.repository = repository;
    }

    @Override
    public List<Counteragent> loadAllActive() {
        return repository.findAll()
                .stream()
                .filter(this::isActive)
                .map(mapper::toCounteragent)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public Optional<Counteragent> getById(String string) {
        return Optional.ofNullable(repository.getOne(string))
                .map(mapper::toCounteragent)
                .or(Optional::empty);
    }

    @Override
    public Counteragent create(Counteragent counteragent) {
        counteragent.setId(null);
        DbCounteragent res = repository.save(mapper.toDbCounteragent(counteragent));
        return mapper.toCounteragent(res);
    }

    @Override
    public Optional<Counteragent> update(Counteragent counteragent) {
        Optional<DbCounteragent> optionalTarget = repository.findById(counteragent.getId())
                .filter(this::isActive)
                .map(this::setDeactivated);
        if (optionalTarget.isPresent()) {
            repository.save(optionalTarget.get());
            counteragent.setId(null);
            DbCounteragent res = repository.save(mapper.toDbCounteragent(counteragent));
            return Optional.ofNullable(mapper.toCounteragent(res));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Counteragent> delete(String id) {
        return repository.findById(id)
                .filter(this::isActive)
                .map(this::setDeactivated)
                .map(repository::save)
                .map(mapper::toCounteragent)
                .or(Optional::empty);
    }

    private DbCounteragent setDeactivated(DbCounteragent contract) {
        return Objects.requireNonNull(contract)
                .setDeactivated(INACTIVE_ENTITY_FLAG)
                .setDeactivationDate(LocalDateTime.now());
    }

    private boolean isActive(DbCounteragent contract) {
        return ACTIVE_ENTITY_FLAG.equals(contract.getDeactivated())
                && contract.getDeactivationDate() == null;
    }

}
