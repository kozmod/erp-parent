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

    public void setId(String id) {
        this.id = id;
    }

    public String getCounteragentId() {
        return counteragentId;
    }

    public void setCounteragentId(String counteragentId) {
        this.counteragentId = counteragentId;
    }

    //@Override
    public int getContractType() {
        return contractType;
    }

    public void setContractType(int contractType) {
        this.contractType = contractType;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractSubject() { return contractSubject; }

    public void setContractSubject(String contractSubject) {
        this.contractSubject = contractSubject;
    }

    public static DbContract.contractBuilder builder() {
        return new DbContract.contractBuilder();
    }

    public static class contractBuilder {
        private String id;
        private String counteragentId;
        private int contractType;
        private String contractDate;
        private String contractNumber;
        private String contractSubject;

        private contractBuilder() {
        }

        public DbContract.contractBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public DbContract.contractBuilder withCounteragentId(String counteragentId) {
            this.counteragentId = counteragentId;
            return this;
        }

        public DbContract.contractBuilder withContractType(int contractType) {
            this.contractType = contractType;
            return this;
        }
        public DbContract.contractBuilder withContractDate(String contractDate) {
            this.contractDate = contractDate;
            return this;
        }

        public DbContract.contractBuilder withContractNumber(String contractNumber) {
            this.contractNumber = contractNumber;
            return this;
        }

        public DbContract.contractBuilder withContractSubject(String contractSubject) {
            this.contractSubject = contractSubject;
            return this;
        }

        public DbContract build() {
            DbContract contract = new DbContract();
            contract.setId(id);
            contract.setCounteragentId(counteragentId);
            contract.setContractType(contractType);
            contract.setContractDate(contractDate);
            contract.setContractNumber(contractNumber);
            contract.setContractSubject(contractSubject);
            return contract;
        }
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
