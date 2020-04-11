package ru.aora.erp.domain.service;

import ru.aora.erp.model.entity.db.DbCounteragent;
import ru.aora.erp.model.entity.mapper.CounteragentMapper;
import ru.aora.erp.model.entity.business.Counteragent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aora.erp.repository.jpa.JpaCounteragentRepository;
import ru.aora.erp.utils.common.CommonUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CounteragentService {
    private final JpaCounteragentRepository counteragentRepository;
    private final CounteragentMapper counteragentMapper = CounteragentMapper.INSTANCE;

    @Autowired
    public CounteragentService(JpaCounteragentRepository counteragentRepository) {
        this.counteragentRepository = counteragentRepository;
    }

    public List<Counteragent> loadAll() {
        return counteragentRepository.findAll()
                .stream()
                .map(counteragentMapper::toCounteragent)
                .collect(Collectors.toList());
    }

    public Counteragent update(Counteragent counteragent) {
        DbCounteragent savedCounteragent = counteragentRepository.save(counteragentMapper.toDbCounteragent(counteragent));
        return counteragentMapper.toCounteragent(savedCounteragent);
    }

    public Counteragent create(Counteragent counteragent) {
        DbCounteragent savedCounteragent = counteragentRepository.save(counteragentMapper.toDbCounteragent(counteragent));
        return counteragentMapper.toCounteragent(savedCounteragent);
    }

    public String delete(String counteragentId) {
        CommonUtils.requiredNotBlank(counteragentId);
        counteragentRepository.deleteById(counteragentId);
        return counteragentId;
    }
}



