package ru.aora.erp.presentation.entity.dto.compose;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.StringJoiner;

public final class KsContractCounteragentDto {

    private String KsId;
    private LocalDate garantDate;
    private String ksNumber;
    private BigDecimal garantSum;
    private Boolean ksStatus;
    private String contractId;
    private String contractNumber;
    private String conteragentId;
    private String conteragentName;
    private Long daysToGarantDate;

    public String getKsId() {
        return KsId;
    }

    public KsContractCounteragentDto setKsId(String ksId) {
        KsId = ksId;
        return this;
    }

    public LocalDate getGarantDate() {
        return garantDate;
    }

    public KsContractCounteragentDto setGarantDate(LocalDate garantDate) {
        this.garantDate = garantDate;
        return this;
    }

    public String getKsNumber() {
        return ksNumber;
    }

    public KsContractCounteragentDto setKsNumber(String ksNumber) {
        this.ksNumber = ksNumber;
        return this;
    }

    public BigDecimal getGarantSum() {
        return garantSum;
    }

    public KsContractCounteragentDto setGarantSum(BigDecimal garantSum) {
        this.garantSum = garantSum;
        return this;
    }

    public Boolean getKsStatus() {
        return ksStatus;
    }

    public KsContractCounteragentDto setKsStatus(Boolean ksStatus) {
        this.ksStatus = ksStatus;
        return this;
    }

    public String getContractId() {
        return contractId;
    }

    public KsContractCounteragentDto setContractId(String contractId) {
        this.contractId = contractId;
        return this;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public KsContractCounteragentDto setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
        return this;
    }

    public String getConteragentId() {
        return conteragentId;
    }

    public KsContractCounteragentDto setConteragentId(String conteragentId) {
        this.conteragentId = conteragentId;
        return this;
    }

    public String getConteragentName() {
        return conteragentName;
    }

    public KsContractCounteragentDto setConteragentName(String conteragentName) {
        this.conteragentName = conteragentName;
        return this;
    }

    public Long getDaysToGarantDate() {
        return daysToGarantDate;
    }

    public KsContractCounteragentDto setDaysToGarantDate(Long daysToGarantDate) {
        this.daysToGarantDate = daysToGarantDate;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", KsContractCounteragentDto.class.getSimpleName() + "[", "]")
                .add("KsId='" + KsId + "'")
                .add("garantDate='" + garantDate + "'")
                .add("ksNumber='" + ksNumber + "'")
                .add("garantSum=" + garantSum)
                .add("ksStatus='" + ksStatus + "'")
                .add("contractId='" + contractId + "'")
                .add("contractNumber='" + contractNumber + "'")
                .add("conteragentId='" + conteragentId + "'")
                .add("conteragentName='" + conteragentName + "'")
                .add("daysToGarantDate='" + daysToGarantDate + "'")
                .toString();
    }
}