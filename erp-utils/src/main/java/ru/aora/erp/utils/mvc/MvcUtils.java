package ru.aora.erp.utils.mvc;

public final class MvcUtils {
    private static final String REDIRECT = "redirect:";
    private static final String FORWARD = "forward:";

    public static String redirectTo(String mapping) {
        return REDIRECT.concat(mapping);
    }

    public static String forwardTo(String mapping) {
        return FORWARD.concat(mapping);
    }
}
