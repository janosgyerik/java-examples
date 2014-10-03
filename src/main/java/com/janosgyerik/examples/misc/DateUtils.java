package com.janosgyerik.examples.misc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static int getElapsedMonths(String datestr) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datestr);
        return getElapsedMonthsSince(date);
    }

    public static int getElapsedMonthsSince(Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        int cmp = c1.compareTo(c2);
        if (cmp == 0) {
            return 0;
        }
        int diff = 0;
        if (cmp > 0) {
            Calendar tmp = c1;
            c1 = c2;
            c2 = tmp;
        }
        while (c1.before(c2)) {
            ++diff;
            c1.add(Calendar.MONTH, 1);
        }
        --diff;
        return cmp < 0 ? diff : -diff;
    }
}