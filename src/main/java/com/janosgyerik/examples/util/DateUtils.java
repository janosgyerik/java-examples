package com.janosgyerik.examples.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    /**
     * Create a (truncated) date object from year, month, day.
     * Throws IllegalArgumentException if the parameters don't form
     * a valid date, for example 2014, 2, 31 or 2014, 99, 99
     *
     * @param year Year, for example 2014
     * @param month Month, for example 2 for February
     * @param day Day, for example 31st (of December)
     * @return truncated date (without hours, minutes, seconds and others)
     */
    public static Date create(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        validate(year, month, day, calendar);
        return calendar.getTime();
    }

    private static void validate(int year, int month, int day, Calendar calendar) {
        if (calendar.get(Calendar.DAY_OF_MONTH) != day) {
            throw new IllegalArgumentException(String.format("Invalid day=%s for year=%s and month=%s", day, year, month));
        }
        if (calendar.get(Calendar.MONTH) != month - 1) {
            throw new IllegalArgumentException(String.format("Invalid month=%s for year=%s and day=%s", month, year, day));
        }
    }

    public static String formatYMD(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static String getMonthShortName(Date date) {
        return new SimpleDateFormat("MMM").format(date);
    }

    public static String getMonthLongName(Date date) {
        return new SimpleDateFormat("MMMMM").format(date);
    }

    public static String getDayShortName(Date date) {
        return new SimpleDateFormat("E").format(date);
    }

    public static String getDayLongName(Date date) {
        return new SimpleDateFormat("EEEEE").format(date);
    }
}
