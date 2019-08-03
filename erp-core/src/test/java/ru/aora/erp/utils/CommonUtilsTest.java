package ru.aora.erp.utils;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class CommonUtilsTest {

    @Test
    public void shouldCountDaysToCurrentDateFromPast() {
        final long daysToCurrentDateValue = 11111L;
        LocalDate date = LocalDate.now().minusDays(daysToCurrentDateValue);
        Long daysTuCurrentDate = CommonUtils.daysToCurrentDate(date);

        assertNotNull(daysTuCurrentDate);
        assertEquals(daysToCurrentDateValue, daysTuCurrentDate.longValue());
    }

    @Test
    public void shouldCountDaysToCurrentDateFromFuture() {
        final long daysTuCurrentDateValue = 730L;
        LocalDate date = LocalDate.now().plusDays(daysTuCurrentDateValue);
        Long daysTuCurrentDate = CommonUtils.daysToCurrentDate(date);

        assertNotNull(daysTuCurrentDate);
        assertEquals((-1 * daysTuCurrentDateValue), daysTuCurrentDate.longValue());
    }

    @Test
    public void shouldCountDaysToCurrentDate() {
        final int daysToCurrentDateValue = 0;
        LocalDate date = LocalDate.of(2022, 1, 13);
        Long daysTuCurrentDate = CommonUtils.daysToCurrentDate(date);
        System.out.println(daysTuCurrentDate);
        //assertNotNull(daysTuCurrentDate);
        //assertEquals(daysToCurrentDateValue, daysTuCurrentDate.intValue());
    }
}
