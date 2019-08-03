package ru.aora.erp.model.entity.mapper;

import javax.annotation.processing.Generated;
import ru.aora.erp.model.entity.business.Counteragent;
import ru.aora.erp.model.entity.db.DbCounteragent;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-08-03T21:56:34+0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class CounteragentMapperImpl implements CounteragentMapper {

    @Override
    public Counteragent toCounteragent(DbCounteragent dbCounteragent) {
        if ( dbCounteragent == null ) {
            return null;
        }

        Counteragent counteragent = new Counteragent();

        counteragent.setId( dbCounteragent.getId() );
        counteragent.setCounteragentName( dbCounteragent.getCounteragentName() );
        counteragent.setGroupName( dbCounteragent.getGroupName() );
        counteragent.setDirectorFirstName( dbCounteragent.getDirectorFirstName() );
        counteragent.setDirectorSurname( dbCounteragent.getDirectorSurname() );
        counteragent.setDirectorPatronymic( dbCounteragent.getDirectorPatronymic() );
        counteragent.setPhoneNumber( dbCounteragent.getPhoneNumber() );
        counteragent.setMail( dbCounteragent.getMail() );
        counteragent.setAddress( dbCounteragent.getAddress() );

        return counteragent;
    }

    @Override
    public DbCounteragent toDbCounteragent(Counteragent counteragent) {
        if ( counteragent == null ) {
            return null;
        }

        DbCounteragent dbCounteragent = new DbCounteragent();

        dbCounteragent.setId( counteragent.getId() );
        dbCounteragent.setCounteragentName( counteragent.getCounteragentName() );
        dbCounteragent.setGroupName( counteragent.getGroupName() );
        dbCounteragent.setDirectorFirstName( counteragent.getDirectorFirstName() );
        dbCounteragent.setDirectorSurname( counteragent.getDirectorSurname() );
        dbCounteragent.setDirectorPatronymic( counteragent.getDirectorPatronymic() );
        dbCounteragent.setPhoneNumber( counteragent.getPhoneNumber() );
        dbCounteragent.setMail( counteragent.getMail() );
        dbCounteragent.setAddress( counteragent.getAddress() );

        return dbCounteragent;
    }
}
