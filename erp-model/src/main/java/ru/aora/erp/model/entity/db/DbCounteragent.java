package ru.aora.erp.model.entity.db;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.StringJoiner;

import static ru.aora.erp.model.entity.db.DbConstant.ACTIVE_ENTITY_FLAG;

@Entity
@Table(name = "Counteragent")
public class DbCounteragent implements Serializable {

    private static final long serialVersionUID = 8481999979205249004L;

    @Id
    @GenericGenerator(name = "generator", strategy = "guid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition="uniqueidentifier")
    private String id;

    @Column(name = "counteragent_name")
    private String counteragentName;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "first_name")
    private String directorFirstName;

    @Column(name = "surname")
    private String directorSurname;

    @Column(name = "patronymic")
    private String directorPatronymic;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mail")
    private String mail;

    @Column(name = "address")
    private String address;

    @Column(name = "deactivation_date")
    private LocalDateTime deactivationDate;

    @Column(name = "deactivated")
    private Integer deactivated;

    @PrePersist
    private void prePersist(){
        if(deactivated == null){
            deactivated = ACTIVE_ENTITY_FLAG;
        }
    }

    public String getId() {
        return id;
    }

    public DbCounteragent setId(String id) {
        this.id = id;
        return this;
    }

    public String getCounteragentName() {
        return counteragentName;
    }

    public DbCounteragent setCounteragentName(String counteragentName) {
        this.counteragentName = counteragentName;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public DbCounteragent setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getDirectorFirstName() {
        return directorFirstName;
    }

    public DbCounteragent setDirectorFirstName(String directorFirstName) {
        this.directorFirstName = directorFirstName;
        return this;
    }

    public String getDirectorSurname() {
        return directorSurname;
    }

    public DbCounteragent setDirectorSurname(String directorSurname) {
        this.directorSurname = directorSurname;
        return this;
    }

    public String getDirectorPatronymic() {
        return directorPatronymic;
    }

    public DbCounteragent setDirectorPatronymic(String directorPatronymic) {
        this.directorPatronymic = directorPatronymic;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public DbCounteragent setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public DbCounteragent setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public DbCounteragent setAddress(String address) {
        this.address = address;
        return this;
    }

    public LocalDateTime getDeactivationDate() {
        return deactivationDate;
    }

    public DbCounteragent setDeactivationDate(LocalDateTime deactivationDate) {
        this.deactivationDate = deactivationDate;
        return this;
    }

    public Integer getDeactivated() {
        return deactivated;
    }

    public DbCounteragent setDeactivated(Integer deactivated) {
        this.deactivated = deactivated;
        return this;
    }

    @Override
    public String toString() {
        return "DbCounteragent{" +
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


