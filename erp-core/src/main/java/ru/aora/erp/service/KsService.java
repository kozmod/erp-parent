package ru.aora.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.model.entity.mapper.KsMapper;
import ru.aora.erp.repository.jpa.DbKsRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;
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
        return DaysBetweenDatesList(KsRepository.findAll()
                .stream().map(ksMapper::toKs)
                .collect(Collectors.toList()));

    }
    public Ks DaysBetweenDates(Ks ks) {

        LocalDate currentDate = LocalDate.now();
        LocalDate endDate=ks.getGarantDate();
        if (endDate==null){}
        //else if(endDate==""){}
        else{
            ks.setDaysToGarantDate(DAYS.between(currentDate, endDate));
        }
        return ks;
    }
    public List<Ks> DaysBetweenDatesList(List ks) {
        Ks[] array = new Ks[ks.size()];
        ks.toArray(array);
        for (int i=0; i < ks.size(); i++) {
            LocalDate currentDate = LocalDate.now();
            LocalDate endDate = array[i].getGarantDate();
            if (endDate == null) {
            }
            //else if(endDate==""){}
            else {

                array[i].setDaysToGarantDate(DAYS.between(currentDate, endDate));
            }

        }
        ks = Arrays.asList(array);
        return ks;
    }

    public void update(Ks ks) {
        ks=DaysBetweenDates(ks);
        requireNonNull(ks.getId());
        KsRepository.save(ksMapper.toDbKs(ks));
    }

    public void create(Ks ks) {
            ks=DaysBetweenDates(ks);
            KsRepository.save(ksMapper.toDbKs(ks));
    }

    public void delete(String KsId) {
        KsRepository.deleteById(KsId);
    }
}



