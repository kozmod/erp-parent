package ru.aora.erp.service.impl;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.aora.erp.model.entity.mapper.CounteragentMapper;
import ru.aora.erp.model.entity.business.Counteragent;
import ru.aora.erp.repository.crud.counteragent.DbCounteragentRepository;
import ru.aora.erp.service.CounteragentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounteragentServiceImpl implements CounteragentService {
    private final DbCounteragentRepository counteragentRepository;
    private final CounteragentMapper counteragentMapper = CounteragentMapper.INSTANCE;

    @Autowired
    public CounteragentServiceImpl(DbCounteragentRepository counteragentRepository) {
        this.counteragentRepository = counteragentRepository;
    }

    public Counteragent getByName(String name) throws UsernameNotFoundException {
        try {
            return counteragentRepository.findByName(name)
                    .map(counteragentMapper::toCounteragent)
                    .orElseThrow(() -> new UsernameNotFoundException(name));
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("Counteragent not found by name: ", name), ex);
        }
    }

    public List<Counteragent> loadAll() {
        return counteragentRepository.findAll()
                .stream()
                .map(counteragentMapper::toCounteragent)
                .collect(Collectors.toList());
    }

    public void update(Counteragent counteragent) {
        try {
            counteragentRepository.update(counteragentMapper.toDbCounteragent(counteragent));
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("Counteragent not found by id"), ex);
        }
    }

    public void create(Counteragent counteragent) {
        try {
            counteragentRepository.create(counteragentMapper.toDbCounteragent(counteragent));
        } catch (Exception ex) {
            throw new UsernameNotFoundException(String.join("Counteragent not found by id"), ex);
        }
    }

    public void delete(String counteragentId) {
        counteragentRepository.delete(counteragentId);
    }
}



