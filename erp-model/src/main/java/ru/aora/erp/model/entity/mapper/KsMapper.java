package ru.aora.erp.model.entity.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.model.entity.db.DbKs;

@Mapper(
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface KsMapper {

    KsMapper INSTANCE = Mappers.getMapper(KsMapper.class);

    Ks toKs(DbKs dbKs);

    DbKs toDbKs(Ks ks);
}
