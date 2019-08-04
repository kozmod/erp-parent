package ru.aora.erp.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class CommonUtils {

    public static Long daysToCurrentDate(LocalDate date) {
        return date != null
                ? ChronoUnit.DAYS.between(LocalDate.now(), date)
                : null;
    }
}
