package ru.aora.erp.model.entity.converter;

import ru.aora.erp.model.entity.counteragent.Counteragent;
import ru.aora.erp.model.entity.db.DbCounteragent;

import static java.util.Objects.requireNonNull;

public class CounteragentConverter {

    public CounteragentConverter() {

    }

    public Counteragent convert(DbCounteragent dbCounteragent) {
        requireNonNull(dbCounteragent, "DbCounteragent should not be null");
        final var counteragent = new Counteragent();
        counteragent.setId(dbCounteragent.getId());
        counteragent.setCounteragentName(dbCounteragent.getCounteragentName());
        counteragent.setGroupName(dbCounteragent.getGroupName());
        counteragent.setDirectorFirstName(dbCounteragent.getDirectorFirstName());
        counteragent.setDirectorSurname(dbCounteragent.getDirectorSurname());
        counteragent.setDirectorPatronymic(dbCounteragent.getDirectorPatronymic());
        counteragent.setPhoneNumber(dbCounteragent.getPhoneNumber());
        counteragent.setMail(dbCounteragent.getMail());
        counteragent.setAddress(dbCounteragent.getAddress());

        return counteragent;
    }


    public DbCounteragent convert(Counteragent counteragent) {
        requireNonNull(counteragent, "DbCounteragent should not be null");
        final var dbCounteragent = new DbCounteragent();
        dbCounteragent.setId(counteragent.getId());
        dbCounteragent.setCounteragentName(counteragent.getCounteragentName());
        dbCounteragent.setGroupName(counteragent.getGroupName());
        dbCounteragent.setDirectorFirstName(counteragent.getDirectorFirstName());
        dbCounteragent.setDirectorSurname(counteragent.getDirectorSurname());
        dbCounteragent.setDirectorPatronymic(counteragent.getDirectorPatronymic());
        dbCounteragent.setPhoneNumber(counteragent.getPhoneNumber());
        dbCounteragent.setMail(counteragent.getMail());
        dbCounteragent.setAddress(counteragent.getAddress());

        return dbCounteragent;
    }




}
