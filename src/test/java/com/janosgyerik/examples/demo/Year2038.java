package com.janosgyerik.examples.demo;

import org.joda.time.DateTime;
import org.junit.Test;

public class Year2038 {
    @Test
    public void test() {
        int booking_year2 = 2038;
        int booking_month2 = 1;
        int booking_day2 = 17;
        DateTime dt = new DateTime().withYear(booking_year2).withMonthOfYear(booking_month2).withDayOfMonth(booking_day2);
        System.out.println(dt);
        System.out.println(dt.plusDays(1));
        System.out.println(dt.plusDays(2));
        System.out.println(dt.plusDays(3));
        System.out.println(dt.plusYears(3));
        System.out.println(dt.plusYears(3).getMillis());
        System.out.println(new DateTime(2039, 1, 12, 0, 0));
        DateTime date = new DateTime(2147451300000L);
        System.out.println(date);
        System.out.println(date.getMillis());
        System.out.println(date.plusDays(1));
        System.out.println(date.plusDays(1).getMillis());
        //        Time t = new Time(new Date().getTime());
        //        t.set(booking_day2, booking_month2 - 1, booking_year2);
        //        long date3 = t.toMillis(false);

        booking_year2 = 2038;
        booking_month2 = 1;
        booking_day2 = 18;
    }
}
