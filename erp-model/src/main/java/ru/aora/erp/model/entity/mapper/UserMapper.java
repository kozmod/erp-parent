package ru.aora.erp.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.aora.erp.model.entity.business.UserAuthority;
import ru.aora.erp.model.entity.db.user.DbModuleRolePair;
import ru.aora.erp.model.entity.db.user.DbUser;
import ru.aora.erp.model.entity.business.User;

import java.util.*;


@Mapper(
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(DbUser dbUser);

    default Collection<UserAuthority> toAuthorities(Collection<DbModuleRolePair> dbAuthorities) {
        if (dbAuthorities == null) {
            return null;
        }
        Collection<UserAuthority> authorities = new ArrayList<>();
        for (DbModuleRolePair pair : dbAuthorities) {
            authorities.add(new UserAuthority(pair.getModuleName(), pair.getRoleName()));
        }
        return authorities;
    }

    DbUser toDbUser(User user);

    default Collection<DbModuleRolePair> toDbAuthorities(Collection<UserAuthority> authorities) {
        if (authorities == null) {
            return null;
        }
        Collection<DbModuleRolePair> dbAuthorities = new ArrayList<>();
        for (UserAuthority authority : authorities) {
            dbAuthorities.add(
                    new DbModuleRolePair()
                            .setModuleName(authority.getModuleName())
                            .setRoleName(authority.getRoleName())
            );
        }
        return dbAuthorities;
    }
}
