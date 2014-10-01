package com.janosgyerik.examples.files.csv.curves;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class CurveListCsvParserTest {

    private CurveListCsvParser parser = new CurveListCsvParser();

    @Test
    public void testParse1Curve() {
        String sample = "header;months;foo;bar;baz\n" +
                "label;2M;1.2;2.2;3.2\n" +
                "label;6M;1.6;2.6;3.6\n";
        CurveListCsvParser.Result result = parser.parse(new Scanner(sample), new Date(), Arrays.asList("foo"));
        assertEquals(1, result.getCurves().size());
    }

    @Test
    public void testParse2Curves() {
        String sample = "header;months;foo;bar;baz\n" +
                "label;2M;1.2;2.2;3.2\n" +
                "label;6M;1.6;2.6;3.6\n";
        CurveListCsvParser.Result result = parser.parse(new Scanner(sample), new Date(), Arrays.asList("foo", "bar"));
        assertEquals(2, result.getCurves().size());
    }

    @Test
    public void testParse3Curves() {
        String sample = "header;months;foo;bar;baz\n" +
                "label;2M;1.2;2.2;3.2\n" +
                "label;6M;1.6;2.6;3.6\n";
        CurveListCsvParser.Result result =
                parser.parse(new Scanner(sample), new Date(), Arrays.asList("foo", "bar", "baz"));
        assertEquals(3, result.getCurves().size());
    }

    @Test
    public void testParse2Rows() {
        String sample = "header;months;foo;bar;baz\n" +
                "label;2M;1.2;2.2;3.2\n" +
                "label;6M;1.6;2.6;3.6\n";
        CurveListCsvParser.Result result = parser.parse(new Scanner(sample), new Date(), Arrays.asList("foo", "bar"));
        assertEquals(2, result.getCurves().iterator().next().getCurvePoints().size());
    }

    @Test
    public void testParse3Rows() {
        String sample = "header;months;foo;bar;baz\n" +
                "label;2M;1.2;2.2;3.2\n" +
                "label;6M;1.6;2.6;3.6\n" +
                "label;6M;1.6;2.6;3.6\n";
        CurveListCsvParser.Result result = parser.parse(new Scanner(sample), new Date(), Arrays.asList("foo", "bar"));
        assertEquals(3, result.getCurves().iterator().next().getCurvePoints().size());
    }

    @Test
    public void testParse3RowsWithMissing() {
        String sample = "header;months;foo;bar;baz\n" +
                "label;2M;1.2;2.2;3.2\n" +
                "label;6M;;2.6;3.6\n" +
                "label;6M;1.6;2.6;3.6\n";
        CurveListCsvParser.Result result = parser.parse(new Scanner(sample), new Date(), Arrays.asList("foo", "bar"));
        assertEquals(2, result.getCurves().iterator().next().getCurvePoints().size());
        assertEquals(0, result.getParseErrors().size());
    }

    @Test
    public void testParse3RowsWithError() {
        String sample = "header;months;foo;bar;baz\n" +
                "label;2M;1.2;2.2;3.2\n" +
                "label;6M;x;2.6;3.6\n" +
                "label;6M;1.6;2.6;3.6\n";
        CurveListCsvParser.Result result = parser.parse(new Scanner(sample), new Date(), Arrays.asList("foo", "bar"));
        assertEquals(2, result.getCurves().iterator().next().getCurvePoints().size());
        assertEquals(1, result.getParseErrors().size());

        Iterator<Curve> iter = result.getCurves().iterator();
        iter.next();
        Curve curve = iter.next();
        assertEquals("bar", curve.getCurveCode());
        assertEquals(3, curve.getCurvePoints().size());
    }
}
