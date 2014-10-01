package com.janosgyerik.examples.files.csv.curves;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Curve {
    private final String curveCode;
    private final Date curveDate;
    private final List<CurvePoint> curvePoints;

    public Curve(String curveCode, Date curveDate, List<CurvePoint> curvePoints) {
        this.curveCode = curveCode;
        this.curveDate = curveDate;
        this.curvePoints = curvePoints;
    }

    public static Builder builder(String curveCode, Date curveDate) {
        return new Builder(curveCode, curveDate);
    }

    public static class Builder {
        private final String curveCode;
        private final Date curveDate;
        private final List<CurvePoint> curvePoints = new ArrayList<>();

        public Builder(String curveCode, Date curveDate) {
            this.curveCode = curveCode;
            this.curveDate = curveDate;
        }

        Builder addCurvePoint(CurvePoint curvePoint) {
            this.curvePoints.add(curvePoint);
            return this;
        }

        public Curve build() {
            return new Curve(curveCode, curveDate, Collections.unmodifiableList(curvePoints));
        }

        public boolean isEmpty() {
            return curvePoints.isEmpty();
        }
    }

    public String getCurveCode() {
        return curveCode;
    }

    public Date getCurveDate() {
        return curveDate;
    }

    public List<CurvePoint> getCurvePoints() {
        return curvePoints;
    }
}
