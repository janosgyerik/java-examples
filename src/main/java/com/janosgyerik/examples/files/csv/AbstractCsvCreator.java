package com.janosgyerik.examples.files.csv;

import java.io.*;
import java.util.Collection;

public abstract class AbstractCsvCreator<T> implements CsvCreator<T> {

    protected static final String DEFAULT_SEPARATOR = ",";
    private static final String DEFAULT_NEWLINE = System.getProperty("line.separator");

    private final String separator;
    private final String newline;

    public AbstractCsvCreator(String separator, String newline) {
        this.separator = separator;
        this.newline = newline;
    }

    public AbstractCsvCreator(String separator) {
        this(separator, DEFAULT_NEWLINE);
    }

    public AbstractCsvCreator() {
        this(DEFAULT_SEPARATOR, DEFAULT_NEWLINE);
    }

    @Override
    public void create(Collection<T> items, CsvColumnizer<T> csvColumnizer) throws IOException {
        try (Writer writer = getWriter()) {
            writeLine(writer, csvColumnizer.getColumnHeaders());
            for (T item : items) {
                writeLine(writer, csvColumnizer.getColumnValues(item));
            }
        }
    }

    protected abstract Writer getWriter() throws FileNotFoundException;

    private void writeLine(Writer writer, Object[] columns) throws IOException {
        String line = toCsv(columns);
        writer.append(line);
        writer.append(newline);
    }

    private String toCsv(Object[] columns) {
        StringBuilder builder = new StringBuilder();
        builder.append(columns[0]);
        for (int i = 1; i < columns.length; ++i) {
            builder.append(separator).append(columns[i]);
        }
        return builder.toString();
    }
}
