package ru.aora.erp.utils.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class CommonUtils {

    public static Long daysToCurrentDate(LocalDate date) {
        return date != null
                ? ChronoUnit.DAYS.between(LocalDate.now(), date)
                : null;
    }

    public static String requiredNotBlank(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Value is blank: " + value);
        }
        return value;
    }

    public static String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
