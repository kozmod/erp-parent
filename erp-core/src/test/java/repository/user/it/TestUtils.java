package repository.user.it;

import java.nio.charset.StandardCharsets;
import java.util.Random;

@SuppressWarnings("WeakerAccess")
public final class TestUtils {

    private TestUtils() {
    }

    public static String newRandomString(int length){
        final byte[] array = new byte[length];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}
