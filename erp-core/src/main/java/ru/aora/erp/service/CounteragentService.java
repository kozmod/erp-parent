package ru.aora.erp.service;

import ru.aora.erp.model.entity.mapper.CounteragentMapper;
import ru.aora.erp.model.entity.business.Counteragent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aora.erp.repository.jpa.DbCounteragentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
@Transactional
public class CounteragentService {
    private final DbCounteragentRepository counteragentRepository;
    private final CounteragentMapper counteragentMapper = CounteragentMapper.INSTANCE;

    @Autowired
    public CounteragentService(DbCounteragentRepository counteragentRepository) {
        this.counteragentRepository = counteragentRepository;
    }

    public List<Counteragent> loadAll() {
        return counteragentRepository.findAll()
                .stream()
                .map(counteragentMapper::toCounteragent)
                .collect(Collectors.toList());
    }

    public void update(Counteragent counteragent) {
        counteragentRepository.save(counteragentMapper.toDbCounteragent(counteragent));
    }

    public void create(Counteragent counteragent) {
        counteragentRepository.save(counteragentMapper.toDbCounteragent(counteragent));
    }

    public void delete(String counteragentId) {
        counteragentRepository.deleteById(counteragentId);
    }
}



