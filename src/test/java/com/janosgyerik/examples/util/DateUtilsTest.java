package com.janosgyerik.examples.util;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateUtilsTest {
    @Test
    public void test_create_2014_12_03() {
        Date date = DateUtils.create(2014, 12, 3);
        assertEquals("2014-12-03", DateUtils.formatYMD(date));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_2014_02_31() {
        DateUtils.create(2014, 2, 31);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_2014_02_m1() {
        DateUtils.create(2014, 2, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_2014_13_01() {
        DateUtils.create(2014, 13, 1);
    }

    @Test
    public void test_getMonthShortName() {
        Date date = DateUtils.create(2014, 12, 3);
        assertEquals("Dec", DateUtils.getMonthShortName(date));
    }

    @Test
    public void test_getMonthLongName() {
        Date date = DateUtils.create(2014, 12, 3);
        assertEquals("December", DateUtils.getMonthLongName(date));
    }

    @Test
    public void test_getDayShortName() {
        Date date = DateUtils.create(2014, 12, 3);
        assertEquals("Wed", DateUtils.getDayShortName(date));
    }

    @Test
    public void test_getDayLongName() {
        Date date = DateUtils.create(2014, 12, 3);
        assertEquals("Wednesday", DateUtils.getDayLongName(date));
    }
}
