package ru.aora.erp.model.entity.converter;


import ru.aora.erp.model.entity.Ks.Ks;
import ru.aora.erp.model.entity.db.DbKs;

import static java.util.Objects.requireNonNull;

public class KsConverter {

    public KsConverter() {

    }

    public Ks convert(DbKs dbKs) {
        requireNonNull(dbKs, "DbKs should not be null");
        final var ks = new Ks();
        ks.setId(dbKs.getId());
        ks.setContractId(dbKs.getContractId());
        ks.setKsDate(dbKs.getKsDate());
        ks.setKsNumber(dbKs.getKsNumber());
        ks.setKsSum(dbKs.getKsSum());
        ks.setGarantDate(dbKs.getGarantDate());
        ks.setGarantSum(dbKs.getGarantSum());

        return ks;
    }


    public DbKs convert(Ks ks) {
        requireNonNull(ks, "DbKs should not be null");
        final var dbKs = new DbKs();
        dbKs.setId(ks.getId());
        dbKs.setContractId(ks.getContractId());
        dbKs.setKsDate(ks.getKsDate());
        dbKs.setKsNumber(ks.getKsNumber());
        dbKs.setKsSum(ks.getKsSum());
        dbKs.setGarantDate(ks.getGarantDate());
        dbKs.setGarantSum(ks.getGarantSum());

        return dbKs;
    }




}
