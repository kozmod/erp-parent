package ru.aora.erp.model.entity.db;

import java.math.BigDecimal;
import java.util.StringJoiner;


public class DbKs {

    private String id;
    private String contractId;
    private String ksDate;
    private String ksNumber;
    private BigDecimal ksSum;
    private String garantDate;
    private BigDecimal garantSum;

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

    public String getKsDate() {
        return ksDate;
    }

    public DbKs setKsDate(String ksDate) {
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

    public String getGarantDate() {
        return garantDate;
    }

    public DbKs setGarantDate(String garantDate) {
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

    @Override
    public String toString() {
        return new StringJoiner(", ", DbKs.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("contractId=" + contractId)
                .add("ksDate='" + ksDate + "'")
                .add("ksNumber='" + ksNumber + "'")
                .add("ksSum='" + ksSum + "'")
                .add("garantDate='" + garantDate + "'")
                .add("garantSum='" + garantSum + "'")
                .toString();
    }
}
