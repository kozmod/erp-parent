package ru.aora.erp.model.entity.business;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.StringJoiner;

import static java.time.temporal.ChronoUnit.DAYS;

public class Ks {

    private String id;
    private String contractId;
    private LocalDate ksDate;
    private String ksNumber;
    private BigDecimal ksSum;
    private LocalDate garantDate;
    private BigDecimal garantSum;
    private Boolean ksStatus;
    private long daysToGarantDate;

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

    public Boolean getKsStatus() {
        return ksStatus;
    }

    public Ks setKsStatus(Boolean ksStatus) {
        this.ksStatus = ksStatus;
        return this;
    }

    public long getDaysToGarantDate() {
        return daysToGarantDate;
    }

    public Ks setDaysToGarantDate(long daysToGarantDate) {
        this.daysToGarantDate = daysToGarantDate;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Ks.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("contractId=" + contractId)
                .add("ksDate='" + ksDate + "'")
                .add("ksNumber='" + ksNumber + "'")
                .add("ksSum='" + ksSum + "'")
                .add("garantDate='" + garantDate + "'")
                .add("garantSum='" + garantSum + "'")
                .add("ksStatus='" + ksStatus + "'")
                .add("daysToGarantDate='" + daysToGarantDate + "'")
                .toString();
    }
}
