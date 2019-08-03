package ru.aora.erp.model.entity.mapper;

import javax.annotation.processing.Generated;
import ru.aora.erp.model.entity.business.Contract;
import ru.aora.erp.model.entity.db.DbContract;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-08-03T21:56:35+0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class ContractMapperImpl implements ContractMapper {

    @Override
    public Contract toContract(DbContract dbContract) {
        if ( dbContract == null ) {
            return null;
        }

        Contract contract = new Contract();

        contract.setId( dbContract.getId() );
        contract.setCounteragentId( dbContract.getCounteragentId() );
        contract.setContractType( dbContract.getContractType() );
        contract.setContractDate( dbContract.getContractDate() );
        contract.setContractNumber( dbContract.getContractNumber() );
        contract.setContractSubject( dbContract.getContractSubject() );

        return contract;
    }

    @Override
    public DbContract toDbContract(Contract contract) {
        if ( contract == null ) {
            return null;
        }

        DbContract dbContract = new DbContract();

        dbContract.setId( contract.getId() );
        dbContract.setCounteragentId( contract.getCounteragentId() );
        dbContract.setContractType( contract.getContractType() );
        dbContract.setContractDate( contract.getContractDate() );
        dbContract.setContractNumber( contract.getContractNumber() );
        dbContract.setContractSubject( contract.getContractSubject() );

        return dbContract;
    }
}
