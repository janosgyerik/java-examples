package com.janosgyerik.examples.files.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCsvParser implements CsvParser {

    public static final String DEFAULT_SEPARATOR = "\\s*,\\s*";

    private final String separator;

    AbstractCsvParser(String separator) {
        this.separator = separator;
    }

    AbstractCsvParser() {
        this(DEFAULT_SEPARATOR);
    }

    abstract String nextLine() throws IOException;

    @Override
    public <T> List<T> readLines(RowMapper<T> mapper) throws IOException {
        List<T> lines = new ArrayList<>();
        {
            String line;
            while ((line = nextLine()) != null) {
                String[] cols = line.split(separator);
                if (mapper.isValidRow(cols)) {
                    lines.add(mapper.mapRow(cols));
                }
            }
        }
        return lines;
    }
}
