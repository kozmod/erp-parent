package ru.aora.erp.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.aora.erp.model.entity.business.UserAuthority;
import ru.aora.erp.model.entity.db.user.DbAuthority;
import ru.aora.erp.model.entity.db.user.DbSubAuthority;
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

    default Collection<UserAuthority> toAuthorities(Collection<DbAuthority> dbAuthorities) {
        if(dbAuthorities == null){
            return null;
        }
        Collection<UserAuthority> authorities = new ArrayList<>();
        for (DbAuthority dbAuthority : dbAuthorities) {
            String rooName = dbAuthority.getName();
            for (DbSubAuthority dbSubAuthority : dbAuthority.getSubAuthorities()) {
                authorities.add(new UserAuthority(rooName, dbSubAuthority.getName()));
            }
        }
        return authorities;
    }

    DbUser toDbUser(User user);

    default Collection<DbAuthority> toDbAuthorities(Collection<UserAuthority> authorities) {
        if(authorities == null){
            return null;
        }
        Map<String, List<String>> authorityMap = new HashMap<>(authorities.size(), 1.1f);
        for (UserAuthority authority : authorities) {
            List<String> dbAuthorityName = authorityMap.computeIfAbsent(authority.getRootAuthority(), k -> new ArrayList<>());
            dbAuthorityName.add(authority.getSubAuthority());
        }
        Collection<DbAuthority> dbAuthorities = new ArrayList<>(authorityMap.size());
        for (Map.Entry<String, List<String>> entry : authorityMap.entrySet()) {
            dbAuthorities.add(
                    new DbAuthority()
                            .setName(entry.getKey())
                            .setSubAuthorities(toDbSubAuthority(entry.getValue()))
            );
        }
        return dbAuthorities;

    }

    default Collection<DbSubAuthority> toDbSubAuthority(List<String> names) {
        if(names == null){
            return null;
        }
        Collection<DbSubAuthority> subAuthorities = new ArrayList<>(names.size());
        for (String name : names) {
            subAuthorities.add(new DbSubAuthority().setName(name));
        }
        return subAuthorities;
    }
}
