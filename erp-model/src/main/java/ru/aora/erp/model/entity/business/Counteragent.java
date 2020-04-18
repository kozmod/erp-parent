package ru.aora.erp.model.entity.business;
import java.time.LocalDateTime;
import java.util.StringJoiner;

public class Counteragent {

    private String id;
    private String counteragentName;
    private String groupName;
    private String directorFirstName;
    private String directorSurname;
    private String directorPatronymic;
    private String phoneNumber;
    private String mail;
    private String address;
    private LocalDateTime deactivationDate;
    private Integer deactivated;

    public String getId() {
        return id;
    }

    public Counteragent setId(String id) {
        this.id = id;
        return this;
    }

    public String getCounteragentName() {
        return counteragentName;
    }

    public Counteragent setCounteragentName(String counteragentName) {
        this.counteragentName = counteragentName;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public Counteragent setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getDirectorFirstName() {
        return directorFirstName;
    }

    public Counteragent setDirectorFirstName(String directorFirstName) {
        this.directorFirstName = directorFirstName;
        return this;
    }

    public String getDirectorSurname() {
        return directorSurname;
    }

    public Counteragent setDirectorSurname(String directorSurname) {
        this.directorSurname = directorSurname;
        return this;
    }

    public String getDirectorPatronymic() {
        return directorPatronymic;
    }

    public Counteragent setDirectorPatronymic(String directorPatronymic) {
        this.directorPatronymic = directorPatronymic;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Counteragent setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public Counteragent setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Counteragent setAddress(String address) {
        this.address = address;
        return this;
    }

    public LocalDateTime getDeactivationDate() {
        return deactivationDate;
    }

    public Counteragent setDeactivationDate(LocalDateTime deactivationDate) {
        this.deactivationDate = deactivationDate;
        return this;
    }

    public Integer getDeactivated() {
        return deactivated;
    }

    public Counteragent setDeactivated(Integer deactivated) {
        this.deactivated = deactivated;
        return this;
    }

    @Override
    public String toString() {
        return "Counteragent{" +
                "id='" + id + '\'' +
                ", counteragentName='" + counteragentName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", directorFirstName='" + directorFirstName + '\'' +
                ", directorSurname='" + directorSurname + '\'' +
                ", directorPatronymic='" + directorPatronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                ", deactivationDate=" + deactivationDate +
                ", deactivated=" + deactivated +
                '}';
    }
}
