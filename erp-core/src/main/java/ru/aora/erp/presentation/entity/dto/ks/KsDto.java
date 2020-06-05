package ru.aora.erp.presentation.entity.dto.ks;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.StringJoiner;

public final class KsDto {

    private String id;
    private String contractId;

    @NotNull
    private LocalDate ksDate;

    @NotNull
    private String ksNumber;

    @NotNull
    private BigDecimal ksSum;

    @NotNull
    private LocalDate garantDate;

    @NotNull
    @Positive
    private BigDecimal garantSum;
    private Boolean paymentStatus;
    private Long daysToGarantDate;

    public String getId() {
        return id;
    }

    public KsDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getContractId() {
        return contractId;
    }

    public KsDto setContractId(String contractId) {
        this.contractId = contractId;
        return this;
    }

    public LocalDate getKsDate() {
        return ksDate;
    }

    public KsDto setKsDate(LocalDate ksDate) {
        this.ksDate = ksDate;
        return this;
    }

    public String getKsNumber() {
        return ksNumber;
    }

    public KsDto setKsNumber(String ksNumber) {
        this.ksNumber = ksNumber;
        return this;
    }

    public BigDecimal getKsSum() {
        return ksSum;
    }

    public KsDto setKsSum(BigDecimal ksSum) {
        this.ksSum = ksSum;
        return this;
    }

    public LocalDate getGarantDate() {
        return garantDate;
    }

    public KsDto setGarantDate(LocalDate garantDate) {
        this.garantDate = garantDate;
        return this;
    }

    public BigDecimal getGarantSum() {
        return garantSum;
    }

    public KsDto setGarantSum(BigDecimal garantSum) {
        this.garantSum = garantSum;
        return this;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public KsDto setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public Long getDaysToGarantDate() {
        return daysToGarantDate;
    }

    public KsDto setDaysToGarantDate(Long daysToGarantDate) {
        this.daysToGarantDate = daysToGarantDate;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", KsDto.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("contractId='" + contractId + "'")
                .add("ksDate=" + ksDate)
                .add("ksNumber='" + ksNumber + "'")
                .add("ksSum=" + ksSum)
                .add("garantDate=" + garantDate)
                .add("garantSum=" + garantSum)
                .add("paymentStatus=" + paymentStatus)
                .add("daysToGarantDate=" + daysToGarantDate)
                .toString();
    }
}
