package ru.aora.erp.model.entity.contract;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.StringJoiner;


@Entity
@Table(name = "contract")
public class Contract {

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

    public static Contract.contractBuilder builder() {
        return new Contract.contractBuilder();
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

        public ru.aora.erp.model.entity.contract.Contract.contractBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public ru.aora.erp.model.entity.contract.Contract.contractBuilder withCounteragentId(String counteragentId) {
            this.counteragentId = counteragentId;
            return this;
        }

        public ru.aora.erp.model.entity.contract.Contract.contractBuilder withContractType(int contractType) {
            this.contractType = contractType;
            return this;
        }
        public ru.aora.erp.model.entity.contract.Contract.contractBuilder withContractDate(String contractDate) {
            this.contractDate = contractDate;
            return this;
        }

        public ru.aora.erp.model.entity.contract.Contract.contractBuilder withContractNumber(String contractNumber) {
            this.contractNumber = contractNumber;
            return this;
        }

        public ru.aora.erp.model.entity.contract.Contract.contractBuilder withContractSubject(String contractSubject) {
            this.contractSubject = contractSubject;
            return this;
        }

        public Contract build() {
            Contract contract = new Contract();
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
        return new StringJoiner(", ", Contract.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("counteragentId=" + counteragentId)
                .add("contractType='" + contractType + "'")
                .add("contractDate='" + contractDate + "'")
                .add("contractNumber='" + contractNumber + "'")
                .add("contractSubject='" + contractSubject + "'")
                .toString();
    }
}
