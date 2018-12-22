import org.junit.Ignore;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncoderTest {
    @Test
    @Ignore
    public void shouldEncode() {
        PasswordEncoder encoder =  new BCryptPasswordEncoder();
        System.out.println(encoder.encode("AAAA"));



    }
}
