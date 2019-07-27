package ru.aora.erp.model.entity.db;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.StringJoiner;

@Entity
@Table(name = "Contract")
public class DbContract {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id_contract", columnDefinition="uniqueidentifier")
    private String id;

    @Column(name = "id_counteragent")
    private String counteragentId;

    @Column(name = "idTypeAgreement")
    private int contractType;

    @Column(name = "contract_date")
    private String contractDate;

    @Column(name = "contract_number")
    private String contractNumber;

    @Column(name = "contract_subject")
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
