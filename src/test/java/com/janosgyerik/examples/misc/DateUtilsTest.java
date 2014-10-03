package com.janosgyerik.examples.misc;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateUtilsTest {

    private Date getTodayPlusMonths(int months) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, months);
        cal.add(Calendar.DAY_OF_MONTH, (int) Math.signum((float) months));
        return cal.getTime();
    }

    private void assertElapsedMonths(int months) {
        assertEquals(-months, DateUtils.getElapsedMonthsSince(getTodayPlusMonths(months)));
    }

    @Test
    public void testPlus2() {
        assertElapsedMonths(2);
    }

    @Test
    public void testMinus2() {
        assertElapsedMonths(-2);
    }

    @Test
    public void testSameMonth() {
        assertElapsedMonths(0);
    }

    @Test
    public void testRange() {
        for (int months = -30; months < 30; ++months) {
            assertElapsedMonths(months);
        }
    }
}

