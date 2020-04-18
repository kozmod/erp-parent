package ru.aora.erp.model.entity.business;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.StringJoiner;

public class Contract {

    private String id;
    private String counteragentId;
    private int contractType;
    private LocalDate contractDate;
    private String contractNumber;
    private String contractSubject;
    private LocalDateTime deactivationDate;
    private Integer deactivated;

    public String getId() {
        return id;
    }

    public Contract setId(String id) {
        this.id = id;
        return this;
    }

    public String getCounteragentId() {
        return counteragentId;
    }

    public Contract setCounteragentId(String counteragentId) {
        this.counteragentId = counteragentId;
        return this;
    }

    public int getContractType() {
        return contractType;
    }

    public Contract setContractType(int contractType) {
        this.contractType = contractType;
        return this;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public Contract setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
        return this;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public Contract setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
        return this;
    }

    public String getContractSubject() {
        return contractSubject;
    }

    public Contract setContractSubject(String contractSubject) {
        this.contractSubject = contractSubject;
        return this;
    }

    public LocalDateTime getDeactivationDate() {
        return deactivationDate;
    }

    public Contract setDeactivationDate(LocalDateTime deactivationDate) {
        this.deactivationDate = deactivationDate;
        return this;
    }

    public Integer getDeactivated() {
        return deactivated;
    }

    public Contract setDeactivated(Integer deactivated) {
        this.deactivated = deactivated;
        return this;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id='" + id + '\'' +
                ", counteragentId='" + counteragentId + '\'' +
                ", contractType=" + contractType +
                ", contractDate=" + contractDate +
                ", contractNumber='" + contractNumber + '\'' +
                ", contractSubject='" + contractSubject + '\'' +
                ", deactivationDate=" + deactivationDate +
                ", deactivated=" + deactivated +
                '}';
    }
}
