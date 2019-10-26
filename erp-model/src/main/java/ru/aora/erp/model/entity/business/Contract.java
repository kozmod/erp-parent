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

    @Override
    public String toString() {
        return new StringJoiner(", ", Contract.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("counteragentId='" + counteragentId + "'")
                .add("contractType=" + contractType)
                .add("contractDate=" + contractDate)
                .add("contractNumber='" + contractNumber + "'")
                .add("contractSubject='" + contractSubject + "'")
                .add("deactivationDate=" + deactivationDate)
                .toString();
    }
}
