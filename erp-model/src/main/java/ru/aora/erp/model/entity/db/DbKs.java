package ru.aora.erp.model.entity.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.StringJoiner;

import static ru.aora.erp.model.entity.db.DbConstant.ACTIVE_ENTITY_FLAG;

@Entity
@Table(name = "KS")
public class DbKs implements Serializable {

    private static final long serialVersionUID = 6765412988706551918L;

    @Id
    @GenericGenerator(name = "generator", strategy = "guid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "id_contract")
    private String contractId;

    @Column(name = "ks_date")
    private LocalDate ksDate;

    @Column(name = "ks_number")
    private String ksNumber;

    @Column(name = "ks_sum")
    private BigDecimal ksSum;

    @Column(name = "garant_date")
    private LocalDate garantDate;

    @Column(name = "garant_sum")
    private BigDecimal garantSum;

    @Column(name = "payment_status")
    private Boolean paymentStatus;

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

    public DbKs setId(String id) {
        this.id = id;
        return this;
    }

    public String getContractId() {
        return contractId;
    }

    public DbKs setContractId(String contractId) {
        this.contractId = contractId;
        return this;
    }

    public LocalDate getKsDate() {
        return ksDate;
    }

    public DbKs setKsDate(LocalDate ksDate) {
        this.ksDate = ksDate;
        return this;
    }

    public String getKsNumber() {
        return ksNumber;
    }

    public DbKs setKsNumber(String ksNumber) {
        this.ksNumber = ksNumber;
        return this;
    }

    public BigDecimal getKsSum() {
        return ksSum;
    }

    public DbKs setKsSum(BigDecimal ksSum) {
        this.ksSum = ksSum;
        return this;
    }

    public LocalDate getGarantDate() {
        return garantDate;
    }

    public DbKs setGarantDate(LocalDate garantDate) {
        this.garantDate = garantDate;
        return this;
    }

    public BigDecimal getGarantSum() {
        return garantSum;
    }

    public DbKs setGarantSum(BigDecimal garantSum) {
        this.garantSum = garantSum;
        return this;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public DbKs setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public LocalDateTime getDeactivationDate() {
        return deactivationDate;
    }

    public DbKs setDeactivationDate(LocalDateTime deactivationDate) {
        this.deactivationDate = deactivationDate;
        return this;
    }

    public Integer getDeactivated() {
        return deactivated;
    }

    public DbKs setDeactivated(Integer deactivated) {
        this.deactivated = deactivated;
        return this;
    }

    @Override
    public String toString() {
        return "DbKs{" +
                "id='" + id + '\'' +
                ", contractId='" + contractId + '\'' +
                ", ksDate=" + ksDate +
                ", ksNumber='" + ksNumber + '\'' +
                ", ksSum=" + ksSum +
                ", garantDate=" + garantDate +
                ", garantSum=" + garantSum +
                ", paymentStatus=" + paymentStatus +
                ", deactivationDate=" + deactivationDate +
                ", deactivated=" + deactivated +
                '}';
    }
}
