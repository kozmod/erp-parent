package ru.aora.erp.model.entity.db;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.StringJoiner;

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

//    @Column(name = "creation_date")
//    private LocalDateTime creationDate;

    @Column(name = "deactivation_date")
    private LocalDateTime deactivationDate;

//    @Column(name = "version_timestamp",columnDefinition = "TIMESTAMP")
//    private String  versionTimestamp;
//
//    @Column(name = "entity_uuid", nullable = false)
//    private String entityUuid;

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

//    public LocalDateTime getCreationDate() {
//        return creationDate;
//    }
//
//    public DbCounteragent setCreationDate(LocalDateTime creationDate) {
//        this.creationDate = creationDate;
//        return this;
//    }

    public LocalDateTime getDeactivationDate() {
        return deactivationDate;
    }

    public DbCounteragent setDeactivationDate(LocalDateTime deactivationDate) {
        this.deactivationDate = deactivationDate;
        return this;
    }

//    public String getEntityUuid() {
//        return entityUuid;
//    }
//
//    public DbCounteragent setEntityUuid(String entityUuid) {
//        this.entityUuid = entityUuid;
//        return this;
//    }
//
//    public String  getVersionTimestamp() {
//        return versionTimestamp;
//    }
//
//    public DbCounteragent setVersionTimestamp(String  versionTimestamp) {
//        this.versionTimestamp = versionTimestamp;
//        return this;
//    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DbCounteragent.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("counteragentName='" + counteragentName + "'")
                .add("groupName='" + groupName + "'")
                .add("directorFirstName='" + directorFirstName + "'")
                .add("directorSurname='" + directorSurname + "'")
                .add("directorPatronymic='" + directorPatronymic + "'")
                .add("phoneNumber='" + phoneNumber + "'")
                .add("mail='" + mail + "'")
                .add("address='" + address + "'")
//                .add("creationDate=" + creationDate)
                .add("deactivationDate=" + deactivationDate)
//                .add("versionTimestamp=" + versionTimestamp)
//                .add("entityUuid='" + entityUuid + "'")
                .toString();
    }
}


