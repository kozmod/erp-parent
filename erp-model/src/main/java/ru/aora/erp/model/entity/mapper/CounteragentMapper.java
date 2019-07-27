package ru.aora.erp.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.aora.erp.model.entity.business.Counteragent;
import ru.aora.erp.model.entity.db.DbCounteragent;

@Mapper(
        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CounteragentMapper {

    CounteragentMapper INSTANCE = Mappers.getMapper(CounteragentMapper.class);

    Counteragent toCounteragent(DbCounteragent dbCounteragent);

    DbCounteragent toDbCounteragent(Counteragent counteragent);
}
