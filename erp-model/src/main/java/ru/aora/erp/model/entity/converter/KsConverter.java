package ru.aora.erp.model.entity.converter;


import ru.aora.erp.model.entity.Ks.Ks;
import ru.aora.erp.model.entity.db.DbKs;

import static java.util.Objects.requireNonNull;

public class KsConverter {

    public KsConverter() {

    }

    public Ks convert(DbKs dbKs) {
        requireNonNull(dbKs, "DbKs should not be null");
        return new Ks()
                .setId(dbKs.getId())
                .setContractId(dbKs.getContractId())
                .setKsDate(dbKs.getKsDate())
                .setKsNumber(dbKs.getKsNumber())
                .setKsSum(dbKs.getKsSum())
                .setGarantDate(dbKs.getGarantDate())
                .setGarantSum(dbKs.getGarantSum());
    }


    public DbKs convert(Ks ks) {
        requireNonNull(ks, "DbKs should not be null");
        return new DbKs()
                .setId(ks.getId())
                .setContractId(ks.getContractId())
                .setKsDate(ks.getKsDate())
                .setKsNumber(ks.getKsNumber())
                .setKsSum(ks.getKsSum())
                .setGarantDate(ks.getGarantDate())
                .setGarantSum(ks.getGarantSum());
    }
}
