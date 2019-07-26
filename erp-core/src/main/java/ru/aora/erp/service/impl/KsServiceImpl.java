package ru.aora.erp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.model.entity.mapper.KsMapper;
import ru.aora.erp.repository.crud.ks.DbKsRepository;
import ru.aora.erp.service.KsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KsServiceImpl implements KsService {
    private final DbKsRepository KsRepository;
    private final KsMapper ksMapper;

    @Autowired
    public KsServiceImpl(DbKsRepository KsRepository) {
        this.KsRepository = KsRepository;
        this.ksMapper = KsMapper.INSTANCE;
    }

    public Ks getByName(String name) throws UsernameNotFoundException {
        try {
            return KsRepository.findByName(name)
                    .map(ksMapper::toKs)
                    .orElseThrow(() -> new UsernameNotFoundException(name));
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("Ks not found by name: ", name), ex);
        }
    }

    public List<Ks> loadAll() {
        return KsRepository.findAll()
                .stream()
                .map(ksMapper::toKs)
                .collect(Collectors.toList());
    }

    public void update(Ks ks) {
        try {
            KsRepository.update(ksMapper.toDbKs(ks));
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("Ks not found by id"), ex);
        }
    }

    public void create(Ks ks){
        System.out.println(ks);
        try { KsRepository.create(ksMapper.toDbKs(ks)); }
        catch (Exception ex) { throw new UsernameNotFoundException(String.join("Ks not found by id"), ex); }
    }

    public void delete(String KsId) {
        KsRepository.delete(KsId);
    }
}



