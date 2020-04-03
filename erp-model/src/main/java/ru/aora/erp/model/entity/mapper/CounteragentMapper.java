package ru.aora.erp.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.aora.erp.model.entity.business.Counteragent;
import ru.aora.erp.model.entity.db.DbCounteragent;

@Mapper(
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface CounteragentMapper {

    CounteragentMapper INSTANCE = Mappers.getMapper(CounteragentMapper.class);

    Counteragent toCounteragent(DbCounteragent dbCounteragent);

//    @Mapping(target = "versionTimestamp", ignore = true)
//    @Mapping(target = "creationDate", ignore = true)
//    @Mapping(target = "entityUuid", ignore = true)
    DbCounteragent toDbCounteragent(Counteragent counteragent);
}
