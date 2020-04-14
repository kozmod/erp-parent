package ru.aora.erp.repository.gateway;

import org.springframework.stereotype.Service;
import ru.aora.erp.domain.KsGateway;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.model.entity.db.DbKs;
import ru.aora.erp.model.entity.mapper.KsMapper;
import ru.aora.erp.repository.jpa.JpaKsRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.aora.erp.repository.gateway.DbGatewayUtils.ACTIVE_ENTITY_FLAG;
import static ru.aora.erp.repository.gateway.DbGatewayUtils.INACTIVE_ENTITY_FLAG;

@Service
@Transactional
public class DbKsGateway implements KsGateway {

    private final JpaKsRepository ksRepository;
    private final KsMapper ksMapper = KsMapper.INSTANCE;

    public DbKsGateway(JpaKsRepository ksRepository) {
        this.ksRepository = ksRepository;
    }

    @Override
    public List<Ks> loadAll() {
        return ksRepository.findAll()
                .stream()
                .filter(this::isActive)
                .map(ksMapper::toKs)
                .collect(Collectors.toList());
    }

    @Override
    public Ks create(Ks ks) {
        Objects.requireNonNull(ks);
        DbKs res = ksRepository.save(ksMapper.toDbKs(ks));
        return ksMapper.toKs(res);
    }

    @Override
    public Optional<Ks> update(Ks ks) {
        Objects.requireNonNull(ks);
        Optional<DbKs> optionalTarget = ksRepository.findById(ks.getId())
                .filter(this::isActive)
                .map(this::setDeactivated);
        if (optionalTarget.isPresent()) {
            ksRepository.save(optionalTarget.get());
            ks.setId(null);
            DbKs res = ksRepository.save(ksMapper.toDbKs(ks));
            return Optional.ofNullable(ksMapper.toKs(res));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Ks> delete(String id) {
        return ksRepository.findById(id)
                .filter(this::isActive)
                .map(this::setDeactivated)
                .map(ksRepository::save)
                .map(ksMapper::toKs)
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
