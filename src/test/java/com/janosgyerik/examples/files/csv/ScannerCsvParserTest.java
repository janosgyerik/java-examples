package com.janosgyerik.examples.files.csv;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ScannerCsvParserTest {

    private static class FirstColMapper implements RowMapper<String> {
        @Override
        public String mapRow(String[] cols) {
            return cols[0];
        }

        @Override
        public boolean isValidRow(String[] cols) {
            return true;
        }
    }

    @Test
    public void test_1line() throws IOException {
        CsvParser reader = new ScannerCsvParser("hello");
        assertEquals(1, reader.parseLines(new FirstColMapper()).size());
    }

    @Test
    public void test_3lines() throws IOException {
        CsvParser reader = new ScannerCsvParser("hello\n\nx");
        assertEquals(3, reader.parseLines(new FirstColMapper()).size());
    }

    @Test
    public void test_3NonBlankLines() throws IOException {
        CsvParser reader = new ScannerCsvParser("hello\n\nx");
        assertEquals(2, reader.parseLines(new FirstColMapper() {
            @Override
            public boolean isValidRow(String[] cols) {
                return cols[0].length() > 0;
            }
        }).size());
    }

    @Test
    public void test_NthColMapper() throws IOException {
        class NthColMapper implements RowMapper<String> {
            private final int index;

            NthColMapper(int index) {
                this.index = index;
            }

            @Override
            public String mapRow(String[] cols) {
                return cols[index - 1];
            }

            @Override
            public boolean isValidRow(String[] cols) {
                return cols.length >= index;
            }
        }

        NthColMapper mapper = new NthColMapper(2);
        assertEquals(0, new ScannerCsvParser("hello\n\nx").parseLines(mapper).size());
        assertEquals(1, new ScannerCsvParser("hello\na,b\nx").parseLines(mapper).size());
        assertEquals(1, new ScannerCsvParser("hello\na,b,c\nx").parseLines(mapper).size());
        assertEquals(Arrays.asList("b"), new ScannerCsvParser("hello\na,b,c\nx").parseLines(mapper));
    }
}
