package ru.aora.erp.model.entity.db;
import java.util.StringJoiner;

public class DbContract {

    private String id;
    private String counteragentId;
    private int contractType;
    private String contractDate;
    private String contractNumber;
    private String contractSubject;

    public String getId() {
        return id;
    }

    public DbContract setId(String id) {
        this.id = id;
        return this;
    }

    public String getCounteragentId() {
        return counteragentId;
    }

    public DbContract setCounteragentId(String counteragentId) {
        this.counteragentId = counteragentId;
        return this;
    }

    public int getContractType() {
        return contractType;
    }

    public DbContract setContractType(int contractType) {
        this.contractType = contractType;
        return this;
    }

    public String getContractDate() {
        return contractDate;
    }

    public DbContract setContractDate(String contractDate) {
        this.contractDate = contractDate;
        return this;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public DbContract setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
        return this;
    }

    public String getContractSubject() {
        return contractSubject;
    }

    public DbContract setContractSubject(String contractSubject) {
        this.contractSubject = contractSubject;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DbContract.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("counteragentId=" + counteragentId)
                .add("contractType='" + contractType + "'")
                .add("contractDate='" + contractDate + "'")
                .add("contractNumber='" + contractNumber + "'")
                .add("contractSubject='" + contractSubject + "'")
                .toString();
    }
}
