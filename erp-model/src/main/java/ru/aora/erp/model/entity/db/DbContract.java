package ru.aora.erp.model.entity.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@Entity
@Table(name = "Contract")
public class DbContract implements Serializable {

    private static final long serialVersionUID = -255546718347516732L;

    @Id
    @GenericGenerator(name = "generator", strategy = "guid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "id_counteragent")
    private String counteragentId;

    @Column(name = "id_type_agreement")
    private int contractType;

    @Column(name = "contract_date")
    private LocalDate contractDate;

    @Column(name = "contract_number")
    private String contractNumber;

    @Column(name = "contract_subject")
    private String contractSubject;

//    @Column(name = "creation_date")
//    private LocalDateTime creationDate;

    @Column(name = "deactivation_date")
    private LocalDateTime deactivationDate;

//    @Column(name = "version_timestamp",columnDefinition = "TIMESTAMP")
//    private String  versionTimestamp;

    @Column(name = "entity_uuid", nullable = false)
    private String entityUuid;

    public String getId() {
        return id;
    }

    public DbContract setId(String id) {
        this.id = id;
        return this;
    }

    public String getCounteragentId() {
        return counteragentId;
    }

    public DbContract setCounteragentId(String counteragentId) {
        this.counteragentId = counteragentId;
        return this;
    }

    public int getContractType() {
        return contractType;
    }

    public DbContract setContractType(int contractType) {
        this.contractType = contractType;
        return this;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public DbContract setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
        return this;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public DbContract setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
        return this;
    }

    public String getContractSubject() {
        return contractSubject;
    }

    public DbContract setContractSubject(String contractSubject) {
        this.contractSubject = contractSubject;
        return this;
    }
//
//    public LocalDateTime getCreationDate() {
//        return creationDate;
//    }
//
//    public DbContract setCreationDate(LocalDateTime creationDate) {
//        this.creationDate = creationDate;
//        return this;
//    }

    public LocalDateTime getDeactivationDate() {
        return deactivationDate;
    }

    public DbContract setDeactivationDate(LocalDateTime deactivationDate) {
        this.deactivationDate = deactivationDate;
        return this;
    }

    public String getEntityUuid() {
        return entityUuid;
    }

    public DbContract setEntityUuid(String entityUuid) {
        this.entityUuid = entityUuid;
        return this;
    }

//    public String getVersionTimestamp() {
//        return versionTimestamp;
//    }

//    public DbContract setVersionTimestamp(String versionTimestamp) {
//        this.versionTimestamp = versionTimestamp;
//        return this;
//    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DbContract.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("counteragentId='" + counteragentId + "'")
                .add("contractType=" + contractType)
                .add("contractDate=" + contractDate)
                .add("contractNumber='" + contractNumber + "'")
                .add("contractSubject='" + contractSubject + "'")
//                .add("creationDate=" + creationDate)
                .add("deactivationDate=" + deactivationDate)
//                .add("versionTimestamp='" + versionTimestamp + "'")
                .add("entityUuid='" + entityUuid + "'")
                .toString();
    }
}
