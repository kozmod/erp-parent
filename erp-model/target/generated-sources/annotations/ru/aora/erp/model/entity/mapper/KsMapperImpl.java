package ru.aora.erp.model.entity.mapper;

import javax.annotation.processing.Generated;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.model.entity.db.DbKs;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-08-03T21:56:35+0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class KsMapperImpl implements KsMapper {

    @Override
    public Ks toKs(DbKs dbKs) {
        if ( dbKs == null ) {
            return null;
        }

        Ks ks = new Ks();

        ks.setId( dbKs.getId() );
        ks.setContractId( dbKs.getContractId() );
        ks.setKsDate( dbKs.getKsDate() );
        ks.setKsNumber( dbKs.getKsNumber() );
        ks.setKsSum( dbKs.getKsSum() );
        ks.setGarantDate( dbKs.getGarantDate() );
        ks.setGarantSum( dbKs.getGarantSum() );
        ks.setPaymentStatus( dbKs.getPaymentStatus() );

        return ks;
    }

    @Override
    public DbKs toDbKs(Ks ks) {
        if ( ks == null ) {
            return null;
        }

        DbKs dbKs = new DbKs();

        dbKs.setId( ks.getId() );
        dbKs.setContractId( ks.getContractId() );
        dbKs.setKsDate( ks.getKsDate() );
        dbKs.setKsNumber( ks.getKsNumber() );
        dbKs.setKsSum( ks.getKsSum() );
        dbKs.setGarantDate( ks.getGarantDate() );
        dbKs.setGarantSum( ks.getGarantSum() );
        dbKs.setPaymentStatus( ks.getPaymentStatus() );

        return dbKs;
    }
}
