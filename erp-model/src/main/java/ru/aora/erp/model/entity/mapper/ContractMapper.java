package ru.aora.erp.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.aora.erp.model.entity.business.Contract;
import ru.aora.erp.model.entity.db.DbContract;

@Mapper(
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface ContractMapper {

    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    Contract toContract(DbContract dbContract);

//    @Mapping(target = "versionTimestamp", ignore = true)
//    @Mapping(target = "entityUuid", ignore = true)
//    @Mapping(target = "creationDate", ignore = true)
    DbContract toDbContract(Contract contract);
}