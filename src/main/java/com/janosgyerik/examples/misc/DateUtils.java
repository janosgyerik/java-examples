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
        int diff = 0;
        if (c2.after(c1)) {
            while (c2.after(c1)) {
                c1.add(Calendar.MONTH, 1);
                if (c2.after(c1)) {
                    diff++;
                }
            }
        } else if (c1.after(c2)) {
            while (c1.after(c2)) {
                c1.add(Calendar.MONTH, -1);
                if (c1.after(c2)) {
                    diff--;
                }
            }
        }
        return diff;
    }
}