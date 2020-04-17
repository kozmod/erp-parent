package ru.aora.erp.repository.gateway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.aora.erp.domain.CrudGateway;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.model.entity.db.DbKs;
import ru.aora.erp.model.entity.mapper.KsMapper;

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
public class DbKsGateway implements CrudGateway<Ks, String> {

    private final JpaRepository<DbKs, String> repository;
    private final KsMapper mapper = KsMapper.INSTANCE;

    public DbKsGateway(JpaRepository<DbKs, String> repository) {
        this.repository = repository;
    }

    @Override
    public List<Ks> loadAll() {
        return repository.findAll()
                .stream()
                .filter(this::isActive)
                .map(mapper::toKs)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public Optional<Ks> getById(String string) {
        return Optional.ofNullable(repository.getOne(string))
                .map(mapper::toKs)
                .or(Optional::empty);
    }

    @Override
    public Ks create(Ks ks) {
        DbKs res = repository.save(mapper.toDbKs(ks));
        return mapper.toKs(res);
    }

    @Override
    public Optional<Ks> update(Ks ks) {
        Optional<DbKs> optionalTarget = repository.findById(ks.getId())
                .filter(this::isActive)
                .map(this::setDeactivated);
        if (optionalTarget.isPresent()) {
            repository.save(optionalTarget.get());
            ks.setId(null);
            DbKs res = repository.save(mapper.toDbKs(ks));
            return Optional.ofNullable(mapper.toKs(res));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Ks> delete(String id) {
        return repository.findById(id)
                .filter(this::isActive)
                .map(this::setDeactivated)
                .map(repository::save)
                .map(mapper::toKs)
                .or(Optional::empty);
    }

    private DbKs setDeactivated(DbKs ks) {
        return Objects.requireNonNull(ks)
                .setDeactivated(INACTIVE_ENTITY_FLAG)
                .setDeactivationDate(LocalDateTime.now());
    }

    private boolean isActive(DbKs ks) {
        return ACTIVE_ENTITY_FLAG.equals(ks.getDeactivated())
                && ks.getDeactivationDate() == null;
    }
}
