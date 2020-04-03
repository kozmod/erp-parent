package ru.aora.erp.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.aora.erp.model.entity.db.DbUser;
import ru.aora.erp.model.entity.business.User;


@Mapper(
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "authorities", ignore = true)
    User toUser(DbUser dbUser);

//    @Mapping(target = "versionTimestamp", ignore = true)
//    @Mapping(target = "entityUuid", ignore = true)
//    @Mapping(target = "creationDate", ignore = true)
//    @Mapping(target = "authorities", ignore = true)
    DbUser toDbUser(User user);
}
