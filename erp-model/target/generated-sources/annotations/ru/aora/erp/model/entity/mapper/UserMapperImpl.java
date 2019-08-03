package ru.aora.erp.model.entity.mapper;

import javax.annotation.processing.Generated;
import ru.aora.erp.model.entity.business.User;
import ru.aora.erp.model.entity.db.DbUser;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-08-03T21:56:35+0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUserWithoutAuthorities(DbUser dbUser) {
        if ( dbUser == null ) {
            return null;
        }

        User user = new User();

        user.setId( dbUser.getId() );
        user.setUsername( dbUser.getUsername() );
        user.setPassword( dbUser.getPassword() );
        user.setFirstName( dbUser.getFirstName() );
        user.setSurname( dbUser.getSurname() );
        user.setPatronymic( dbUser.getPatronymic() );
        user.setAccountNonExpired( dbUser.isAccountNonExpired() );
        user.setAccountNonLocked( dbUser.isAccountNonLocked() );
        user.setCredentialsNonExpired( dbUser.isCredentialsNonExpired() );
        user.setEnabled( dbUser.isEnabled() );
        user.setPhoneNumber( dbUser.getPhoneNumber() );
        user.setMail( dbUser.getMail() );
        user.setEmployeePosition( dbUser.getEmployeePosition() );
        user.setDel( dbUser.isDel() );

        return user;
    }

    @Override
    public DbUser toDbUserWithoutModules(User user) {
        if ( user == null ) {
            return null;
        }

        DbUser dbUser = new DbUser();

        dbUser.setId( user.getId() );
        dbUser.setFirstName( user.getFirstName() );
        dbUser.setSurname( user.getSurname() );
        dbUser.setPatronymic( user.getPatronymic() );
        dbUser.setPhoneNumber( user.getPhoneNumber() );
        dbUser.setAccountNonExpired( user.isAccountNonExpired() );
        dbUser.setAccountNonLocked( user.isAccountNonLocked() );
        dbUser.setCredentialsNonExpired( user.isCredentialsNonExpired() );
        dbUser.setEnabled( user.isEnabled() );
        dbUser.setUsername( user.getUsername() );
        dbUser.setPassword( user.getPassword() );
        dbUser.setMail( user.getMail() );
        dbUser.setEmployeePosition( user.getEmployeePosition() );
        dbUser.setDel( user.isDel() );

        return dbUser;
    }
}
