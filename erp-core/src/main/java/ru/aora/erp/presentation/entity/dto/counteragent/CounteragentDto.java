package ru.aora.erp.presentation.entity.dto.counteragent;

import java.util.StringJoiner;

public final class CounteragentDto {

    private String id;
    private String counteragentName;
    private String groupName;
    private String directorFirstName;
    private String directorSurname;
    private String directorPatronymic;
    private String phoneNumber;
    private String mail;
    private String address;

    public String getId() {
        return id;
    }

    public CounteragentDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getCounteragentName() {
        return counteragentName;
    }

    public CounteragentDto setCounteragentName(String counteragentName) {
        this.counteragentName = counteragentName;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public CounteragentDto setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getDirectorFirstName() {
        return directorFirstName;
    }

    public CounteragentDto setDirectorFirstName(String directorFirstName) {
        this.directorFirstName = directorFirstName;
        return this;
    }

    public String getDirectorSurname() {
        return directorSurname;
    }

    public CounteragentDto setDirectorSurname(String directorSurname) {
        this.directorSurname = directorSurname;
        return this;
    }

    public String getDirectorPatronymic() {
        return directorPatronymic;
    }

    public CounteragentDto setDirectorPatronymic(String directorPatronymic) {
        this.directorPatronymic = directorPatronymic;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CounteragentDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public CounteragentDto setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CounteragentDto setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CounteragentDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("counteragentName='" + counteragentName + "'")
                .add("groupName='" + groupName + "'")
                .add("directorFirstName='" + directorFirstName + "'")
                .add("directorSurname='" + directorSurname + "'")
                .add("directorPatronymic='" + directorPatronymic + "'")
                .add("phoneNumber='" + phoneNumber + "'")
                .add("mail='" + mail + "'")
                .add("address='" + address + "'")
                .toString();
    }
}
