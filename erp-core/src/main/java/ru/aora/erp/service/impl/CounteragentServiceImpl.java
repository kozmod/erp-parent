package ru.aora.erp.service.impl;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.aora.erp.model.entity.converter.CounteragentConverter;
import ru.aora.erp.model.entity.counteragent.Counteragent;
import ru.aora.erp.repository.crud.counteragent.DbCounteragentRepository;
import ru.aora.erp.service.CounteragentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounteragentServiceImpl implements CounteragentService {
    private final DbCounteragentRepository counteragentRepository;
    private final CounteragentConverter counteragentConverter;

    @Autowired
    public CounteragentServiceImpl(
            DbCounteragentRepository counteragentRepository
    ) {
        this.counteragentRepository = counteragentRepository;

        this.counteragentConverter = new CounteragentConverter();
    }

    public Counteragent getByName(String name) throws UsernameNotFoundException {
        try {
            return counteragentRepository.findByName(name)
                    .map(counteragentConverter::convert)
                    .orElseThrow(() -> new UsernameNotFoundException(name));
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("Counteragent not found by name: ", name), ex);
        }
    }

    public List<Counteragent> loadAll() {
        return counteragentRepository.findAll()
                .stream()
                .map(counteragentConverter::convert)
                .collect(Collectors.toList());
    }

    public void update(Counteragent counteragent) {
        try {
            counteragentRepository.update(counteragentConverter.convert(counteragent));
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("Counteragent not found by id"), ex);
        }
    }

    public void create(Counteragent counteragent){
        try { counteragentRepository.create(counteragentConverter.convert(counteragent)); }
        catch (Exception ex) { throw new UsernameNotFoundException(String.join("Counteragent not found by id"), ex); }
    }

    public void delete(String counteragentId) {
        counteragentRepository.delete(counteragentId);
    }
}



