package ru.aora.erp.model.entity.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.StringJoiner;

@Entity
@Table(name = "KS")
public class DbKs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_KS")
    private String id;

    @Column(name = "id_contract")
    private String contractId;

    @Column(name = "KS_date")
    private String ksDate;

    @Column(name = "KS_number")
    private String ksNumber;

    @Column(name = "KS_sum")
    private BigDecimal ksSum;

    @Column(name = "Garant_date")
    private String garantDate;

    @Column(name = "Garant_sum")
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
