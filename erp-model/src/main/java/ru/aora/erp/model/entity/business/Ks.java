package ru.aora.erp.model.entity.business;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class Ks {

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

    public String getKsDate() {
        return ksDate;
    }

    public Ks setKsDate(String ksDate) {
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

    public String getGarantDate() {
        return garantDate;
    }

    public Ks setGarantDate(String garantDate) {
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
                .toString();
    }
}
