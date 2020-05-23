package ru.aora.erp.model.entity.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Contract")
public class DbContract implements Serializable,Deactivatable {

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

    @Column(name = "deactivation_date")
    private LocalDateTime deactivationDate;

    @Column(name = "deactivated")
    private Integer activeStatus;

    @PrePersist
    private void prePersist(){
        if(activeStatus == null){
            activeStatus = ACTIVE_ENTITY_FLAG;
        }
    }

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

    public LocalDateTime getDeactivationDate() {
        return deactivationDate;
    }

    public DbContract setDeactivationDate(LocalDateTime deactivationDate) {
        this.deactivationDate = deactivationDate;
        return this;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public DbContract setActiveStatus(Integer deactivated) {
        this.activeStatus = deactivated;
        return this;
    }

    @Override
    public String toString() {
        return "DbContract{" +
                "id='" + id + '\'' +
                ", counteragentId='" + counteragentId + '\'' +
                ", contractType=" + contractType +
                ", contractDate=" + contractDate +
                ", contractNumber='" + contractNumber + '\'' +
                ", contractSubject='" + contractSubject + '\'' +
                ", deactivationDate=" + deactivationDate +
                ", deactivated=" + activeStatus +
                '}';
    }
}
