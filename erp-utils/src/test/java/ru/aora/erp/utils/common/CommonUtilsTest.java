package ru.aora.erp.utils.common;

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
        assertEquals(-1 * daysToCurrentDateValue, daysTuCurrentDate.longValue());
    }

    @Test
    public void shouldCountDaysToCurrentDateFromFuture() {
        final long daysToCurrentDateValue = 730L;
        LocalDate date = LocalDate.now().plusDays(daysToCurrentDateValue);
        Long daysToCurrentDate = CommonUtils.daysToCurrentDate(date);
        System.out.println(daysToCurrentDate);

        assertNotNull(daysToCurrentDate);
        assertEquals(daysToCurrentDateValue, daysToCurrentDate.longValue());
    }

}
