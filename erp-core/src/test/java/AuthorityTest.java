import org.junit.Assert;
import org.junit.Test;
import ru.aora.erp.component.TestModuleAuthority;
import ru.aora.erp.model.entity.IdAuthority;

import java.util.List;

public class AuthorityTest {
    @Test
    public void shouldEquals() {
        List<IdAuthority> firstAuthorities = List.of(TestAuthority.DELETE);
        List<IdAuthority> secondAuthorities = List.of(TestAuthority.DELETE);

        Assert.assertTrue(firstAuthorities.contains(secondAuthorities.get(0)));

    }

    private enum  TestAuthority implements IdAuthority {

        ADD("Добавить"), DELETE("Удалить");

        TestAuthority(String ruleName) {
            this.ruleName = ruleName;
        }

        @Override
        public String getAuthority() {
            return name();
        }

        @Override
        public long getModuleId() {
            return moduleId;
        }

        @Override
        public void setModuleId(long moduleId) {
            TestAuthority.moduleId = moduleId;
        }

        @Override
        public long getRuleId() {
            return ruleId;
        }

        @Override
        public void setRuleId(long ruleId) {
            this.ruleId = ruleId;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getRuleName() {
            return ruleName;
        }

        private static long moduleId;
        private static final String name = "TEST";
        private long ruleId;
        private String ruleName;
    }
}
