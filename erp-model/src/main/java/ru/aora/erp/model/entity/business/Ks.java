package ru.aora.erp.model.entity.business;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.StringJoiner;

public class Ks {

    private String id;
    private String contractId;
    private LocalDate ksDate;
    private String ksNumber;
    private BigDecimal ksSum;
    private LocalDate garantDate;
    private BigDecimal garantSum;
    private Boolean paymentStatus;
    private LocalDateTime deactivationDate;
    private Integer deactivated;

    public String getId() {
        return id;
    }

    public Ks setId(String id) {
        this.id = id;
        return this;
    }

    public String getContractId() {
        return contractId;
    }

    public Ks setContractId(String contractId) {
        this.contractId = contractId;
        return this;
    }

    public LocalDate getKsDate() {
        return ksDate;
    }

    public Ks setKsDate(LocalDate ksDate) {
        this.ksDate = ksDate;
        return this;
    }

    public String getKsNumber() {
        return ksNumber;
    }

    public Ks setKsNumber(String ksNumber) {
        this.ksNumber = ksNumber;
        return this;
    }

    public BigDecimal getKsSum() {
        return ksSum;
    }

    public Ks setKsSum(BigDecimal ksSum) {
        this.ksSum = ksSum;
        return this;
    }

    public LocalDate getGarantDate() {
        return garantDate;
    }

    public Ks setGarantDate(LocalDate garantDate) {
        this.garantDate = garantDate;
        return this;
    }

    public BigDecimal getGarantSum() {
        return garantSum;
    }

    public Ks setGarantSum(BigDecimal garantSum) {
        this.garantSum = garantSum;
        return this;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public Ks setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public LocalDateTime getDeactivationDate() {
        return deactivationDate;
    }

    public Ks setDeactivationDate(LocalDateTime deactivationDate) {
        this.deactivationDate = deactivationDate;
        return this;
    }

    public Integer getDeactivated() {
        return deactivated;
    }

    public Ks setDeactivated(Integer deactivated) {
        this.deactivated = deactivated;
        return this;
    }

    @Override
    public String toString() {
        return "Ks{" +
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
