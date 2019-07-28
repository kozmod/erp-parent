package ru.aora.erp.repository.crud.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.model.entity.db.DbUser;

import java.util.Set;

@SuppressWarnings("WeakerAccess")
public final class DbUserUtils {

    private DbUserUtils() {
    }

    public static DbUser newDbUserWithAuthorities(String userName, Set<DbModule> dbModules) {
        final DbUser user = newDbUserWithOutAuthorities(userName);
        user.setAuthorities(dbModules);
        return user;
    }

    public static DbUser newDbUserWithOutAuthorities(String userName) {
        return new DbUser()
                .setUsername(userName)
                .setPassword(
                        new BCryptPasswordEncoder().encode(userName)
                )
                .setFirstName("Иванов")
                .setSurname("Иван")
                .setPatronymic("Иванович")
                .setAccountNonExpired(true)
                .setAccountNonLocked(true)
                .setCredentialsNonExpired(true)
                .setEnabled(true)
                .setMail("y-mail@gMail.com")
                .setEmployeePosition("Слесарь")
                .setPhoneNumber("+7(926)1057452")
                .setAuthorities(Set.of());
    }
}
