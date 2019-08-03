package ru.aora.erp.utils;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public final class CommonUtilsTest {

    @Test
    public void shouldCountDaysToCurrentDateFromPast() {
        final int daysToCurrentDateValue = 10;
        LocalDate date = LocalDate.now().minusDays(daysToCurrentDateValue);
        Integer daysTuCurrentDate = CommonUtils.daysToCurrentDate(date);

        assertNotNull(daysTuCurrentDate);
        assertEquals(daysToCurrentDateValue, daysTuCurrentDate.intValue());
    }

    @Test
    public void shouldCountDaysToCurrentDateFromFuture() {
        final int daysTuCurrentDateValue = 10;
        LocalDate date = LocalDate.now().plusDays(daysTuCurrentDateValue);
        Integer daysTuCurrentDate = CommonUtils.daysToCurrentDate(date);

        assertNotNull(daysTuCurrentDate);
        assertEquals(daysTuCurrentDateValue, daysTuCurrentDate.intValue());
    }
}
