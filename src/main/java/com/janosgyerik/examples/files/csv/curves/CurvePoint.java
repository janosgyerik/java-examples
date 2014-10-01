package com.janosgyerik.examples.files.csv.curves;

import java.util.Calendar;
import java.util.Date;

public class CurvePoint {

    private final Date curveDate;
    private final int months;
    private final Date pointDate;
    private final double value;

    public CurvePoint(Date curveDate, int months, double value) {
        this.curveDate = curveDate;
        this.months = months;
        this.pointDate = getDatePlusMonths(curveDate, months);
        this.value = value;
    }

    private static Date getDatePlusMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    public Date getCurveDate() {
        return curveDate;
    }

    public int getMonths() {
        return months;
    }

    public Date getPointDate() {
        return pointDate;
    }

    public double getValue() {
        return value;
    }
}
