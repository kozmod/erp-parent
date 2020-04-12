package ru.aora.erp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.model.entity.db.DbKs;
import ru.aora.erp.model.entity.mapper.KsMapper;
import ru.aora.erp.repository.jpa.JpaKsRepository;
import ru.aora.erp.utils.common.CommonUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class KsService {
    private final JpaKsRepository KsRepository;
    private final KsMapper ksMapper = KsMapper.INSTANCE;

    @Autowired
    public KsService(JpaKsRepository KsRepository) {
        this.KsRepository = KsRepository;
    }

    public List<Ks> loadAll() {
        return KsRepository.findAll()
                .stream()
                .map(ksMapper::toKs)
                .collect(Collectors.toList());
    }

    public Ks update(Ks ks) {
        Objects.requireNonNull(ks);
        DbKs updatedKs = KsRepository.save(ksMapper.toDbKs(ks));
        return ksMapper.toKs(updatedKs);
    }

    public Ks create(Ks ks) {
        DbKs createdKs = KsRepository.save(ksMapper.toDbKs(ks));
        return ksMapper.toKs(createdKs);
    }

    public String delete(String KsId) {
        CommonUtils.requiredNotBlank(KsId);
        KsRepository.deleteById(KsId);
        return KsId;
    }
}



