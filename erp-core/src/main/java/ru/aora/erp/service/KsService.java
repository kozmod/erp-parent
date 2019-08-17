package ru.aora.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.model.entity.mapper.KsMapper;
import ru.aora.erp.repository.jpa.DbKsRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
@Transactional
public class KsService {
    private final DbKsRepository KsRepository;
    private final KsMapper ksMapper = KsMapper.INSTANCE;

    @Autowired
    public KsService(DbKsRepository KsRepository) {
        this.KsRepository = KsRepository;
    }

    public List<Ks> loadAll() {
        return KsRepository.findAll()
                .stream()
                .map(ksMapper::toKs)
                .collect(Collectors.toList());

    }

    public void update(Ks ks) {
        KsRepository.save(ksMapper.toDbKs(ks));
    }

    public void create(Ks ks) {
        KsRepository.save(ksMapper.toDbKs(ks));
    }

    public void delete(String KsId) {
        KsRepository.deleteById(KsId);
    }
}



