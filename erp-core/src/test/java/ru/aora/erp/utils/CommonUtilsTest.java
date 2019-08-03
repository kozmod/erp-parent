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
        Long daysTuCurrentDate = CommonUtils.daysToCurrentDate(date);

        assertNotNull(daysTuCurrentDate);
        assertEquals(daysToCurrentDateValue, daysTuCurrentDate.intValue());
    }

    @Test
    public void shouldCountDaysToCurrentDateFromFuture() {
        final int daysTuCurrentDateValue = 10;
        LocalDate date = LocalDate.now().plusDays(daysTuCurrentDateValue);
        Long daysTuCurrentDate = CommonUtils.daysToCurrentDate(date);

        assertNotNull(daysTuCurrentDate);
        assertEquals(daysTuCurrentDateValue, daysTuCurrentDate.intValue());
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
