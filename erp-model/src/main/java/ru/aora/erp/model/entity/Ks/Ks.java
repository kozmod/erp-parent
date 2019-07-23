package ru.aora.erp.model.entity.Ks;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.StringJoiner;


@Entity
@Table(name = "ks")
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

    public void setId(String id) {
        this.id = id;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getKsDate() {
        return ksDate;
    }

    public void setKsDate(String ksDate) {
        this.ksDate = ksDate;
    }

    public String getKsNumber() {
        return ksNumber;
    }

    public void setKsNumber(String ksNumber) {
        this.ksNumber = ksNumber;
    }

    public BigDecimal getKsSum() {
        return ksSum;
    }

    public void setKsSum(BigDecimal ksSum) {
        this.ksSum = ksSum;
    }

    public String getGarantDate() { return garantDate; }

    public void setGarantDate(String garantDate) {
        this.garantDate = garantDate;
    }

    public BigDecimal getGarantSum() {
        return garantSum;
    }

    public void setGarantSum(BigDecimal garantSum) {
        this.garantSum = garantSum;
    }

    public static Ks.contractBuilder builder() {
        return new Ks.contractBuilder();
    }

    public static class contractBuilder {
        private String id;
        private String contractId;
        private String ksDate;
        private String ksNumber;
        private BigDecimal ksSum;
        private String garantDate;
        private BigDecimal garantSum;

        private contractBuilder() {
        }

        public Ks.contractBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public Ks.contractBuilder withContractId(String contractId) {
            this.contractId = contractId;
            return this;
        }

        public Ks.contractBuilder withKsDate(String ksDate) {
            this.ksDate = ksDate;
            return this;
        }
        public Ks.contractBuilder withKsNumber(String ksNumber) {
            this.ksNumber = ksNumber;
            return this;
        }

        public Ks.contractBuilder withKsSum(BigDecimal ksSum) {
            this.ksSum = ksSum;
            return this;
        }

        public Ks.contractBuilder withGarantDate(String garantDate) {
            this.garantDate = garantDate;
            return this;
        }

        public Ks.contractBuilder withGarantSum(BigDecimal garantSum) {
            this.garantSum = garantSum;
            return this;
        }

        public Ks build() {
            Ks ks = new Ks();
            ks.setId(id);
            ks.setContractId(contractId);
            ks.setKsDate(ksDate);
            ks.setKsNumber(ksNumber);
            ks.setKsSum(ksSum);
            ks.setGarantDate(garantDate);
            ks.setGarantSum(garantSum);
            return ks;
        }
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
