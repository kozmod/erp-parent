package ru.aora.erp.entity.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.StringJoiner;

public class KsContractCounteragentDto {

    private String KsId;
    private LocalDate ksDate;
    private String ksNumber;
    private BigDecimal garantSum;
    private String contractId;
    private String contractNumber;
    private String conteragentId;
    private String conteragentName;

    public String getKsId() {
        return KsId;
    }

    public KsContractCounteragentDto setKsId(String ksId) {
        KsId = ksId;
        return this;
    }

    public LocalDate getKsDate() {
        return ksDate;
    }

    public KsContractCounteragentDto setKsDate(LocalDate ksDate) {
        this.ksDate = ksDate;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", KsContractCounteragentDto.class.getSimpleName() + "[", "]")
                .add("KsId='" + KsId + "'")
                .add("ksDate='" + ksDate + "'")
                .add("ksNumber='" + ksNumber + "'")
                .add("garantSum=" + garantSum)
                .add("contractId='" + contractId + "'")
                .add("contractNumber='" + contractNumber + "'")
                .add("conteragentId='" + conteragentId + "'")
                .add("conteragentName='" + conteragentName + "'")
                .toString();
    }
}