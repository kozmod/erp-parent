import org.junit.Test;

import java.util.Map;

public class UtilsTest {

    @Test(expected = UnsupportedOperationException.class)
    public void name() {
        Map<String,Boolean> map = Map.of();
        map.put("AAA",true);
    }
}
