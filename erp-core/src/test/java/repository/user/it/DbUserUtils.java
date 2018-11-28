package repository.user.it;

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
        return DbUser.builder()
                .withUsername(userName)
                .withPassword(
                        new BCryptPasswordEncoder().encode(userName)
                )
                .withFirstName("Иванов")
                .withSurname("Иван")
                .withPatronymic("Иванович")
                .withAccountNonExpired(true)
                .withAccountNonLocked(true)
                .withCredentialsNonExpired(true)
                .withEnabled(true)
                .withMail("y-mail@gMail.com")
                .withEmployeePosition("Слесарь")
                .withPhoneNumber("+7(926)1057452")
                .withAuthorities(Set.of())
                .build();
    }
}
