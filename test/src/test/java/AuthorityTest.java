import org.junit.Test;
import ru.aora.erp.component.TestModuleAuthority;

import static org.junit.Assert.assertEquals;

public class AuthorityTest {

    @Test
    public void shouldReturnEnumRoleName_WhenGetAuthority() {
        assertEquals(
                TestModuleAuthority.ADD.toString(),
                TestModuleAuthority.ADD.getAuthority()
        );
        assertEquals(
                TestModuleAuthority.DELETE.toString(),
                TestModuleAuthority.DELETE.getAuthority()
        );
    }
}
