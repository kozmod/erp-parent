package ru.aora.erp.utils;

import java.time.LocalDate;
import java.time.Period;

import static java.time.temporal.ChronoUnit.DAYS;

public final class CommonUtils {

    public static Long daysToCurrentDate(LocalDate date) {
        return date != null
        //        ? Math.abs(Period.between(date, LocalDate.now()).getDays())
                ? (DAYS.between(date, LocalDate.now()))
                : null;
    }
}
