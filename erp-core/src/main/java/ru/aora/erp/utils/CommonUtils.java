package ru.aora.erp.utils;

import java.time.LocalDate;
import java.time.Period;
public final class CommonUtils {

    public static Integer daysToCurrentDate(LocalDate date) {
        return date != null
                ? Math.abs(Period.between(date, LocalDate.now()).getDays())
                : null;
    }
}
