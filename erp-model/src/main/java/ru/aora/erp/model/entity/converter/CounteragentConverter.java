package ru.aora.erp.model.entity.converter;

import ru.aora.erp.model.entity.counteragent.Counteragent;
import ru.aora.erp.model.entity.db.DbCounteragent;

import static java.util.Objects.requireNonNull;

public class CounteragentConverter {

    public CounteragentConverter() {

    }

    public Counteragent convert(DbCounteragent dbCounteragent) {
        requireNonNull(dbCounteragent, "DbCounteragent should not be null");
        return new Counteragent()
                .setId(dbCounteragent.getId())
                .setCounteragentName(dbCounteragent.getCounteragentName())
                .setGroupName(dbCounteragent.getGroupName())
                .setDirectorFirstName(dbCounteragent.getDirectorFirstName())
                .setDirectorSurname(dbCounteragent.getDirectorSurname())
                .setDirectorPatronymic(dbCounteragent.getDirectorPatronymic())
                .setPhoneNumber(dbCounteragent.getPhoneNumber())
                .setMail(dbCounteragent.getMail())
                .setAddress(dbCounteragent.getAddress());
    }


    public DbCounteragent convert(Counteragent counteragent) {
        requireNonNull(counteragent, "Counteragent should not be null");
        return new DbCounteragent()
                .setId(counteragent.getId())
                .setCounteragentName(counteragent.getCounteragentName())
                .setGroupName(counteragent.getGroupName())
                .setDirectorFirstName(counteragent.getDirectorFirstName())
                .setDirectorSurname(counteragent.getDirectorSurname())
                .setDirectorPatronymic(counteragent.getDirectorPatronymic())
                .setPhoneNumber(counteragent.getPhoneNumber())
                .setMail(counteragent.getMail())
                .setAddress(counteragent.getAddress());
    }
}
