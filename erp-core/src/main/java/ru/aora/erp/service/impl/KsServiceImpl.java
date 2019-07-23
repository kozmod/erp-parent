package ru.aora.erp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aora.erp.model.entity.Ks.Ks;
import ru.aora.erp.model.entity.converter.KsConverter;
import ru.aora.erp.repository.crud.ks.DbKsRepository;
import ru.aora.erp.service.KsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KsServiceImpl implements KsService {
    private final DbKsRepository KsRepository;
    private final KsConverter KsConverter;

    @Autowired
    public KsServiceImpl(DbKsRepository KsRepository) {
        this.KsRepository = KsRepository;

        this.KsConverter = new KsConverter();
    }

    public Ks getByName(String name) throws UsernameNotFoundException {
        try {
            return KsRepository.findByName(name)
                    .map(KsConverter::convert)
                    .orElseThrow(() -> new UsernameNotFoundException(name));
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("Ks not found by name: ", name), ex);
        }
    }

    public List<Ks> loadAll() {
        return KsRepository.findAll()
                .stream()
                .map(KsConverter::convert)
                .collect(Collectors.toList());
    }

    public void update(Ks ks) {
        try {
            KsRepository.update(KsConverter.convert(ks));
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("Ks not found by id"), ex);
        }
    }

    public void create(Ks ks){
        System.out.println(ks);
        try { KsRepository.create(KsConverter.convert(ks)); }
        catch (Exception ex) { throw new UsernameNotFoundException(String.join("Ks not found by id"), ex); }
    }

    public void delete(String KsId) {
        KsRepository.delete(KsId);
    }
}



