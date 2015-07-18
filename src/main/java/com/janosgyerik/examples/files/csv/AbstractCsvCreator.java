package com.janosgyerik.examples.files.csv;

import java.io.*;
import java.util.Collection;

public abstract class AbstractCsvCreator<T> implements CsvCreator<T> {

    protected static final String DEFAULT_SEPARATOR = ",";
    private static final String DEFAULT_NEWLINE = System.getProperty("line.separator");

    private final PrintWriter writer;
    private final String separator;
    private final String newline;

    public AbstractCsvCreator(PrintWriter writer, String separator, String newline) {
        this.writer = writer;
        this.separator = separator;
        this.newline = newline;
    }

    public AbstractCsvCreator(PrintWriter writer, String separator) {
        this(writer, separator, DEFAULT_NEWLINE);
    }

    @Override
    public void create(Collection<T> items, CsvColumnizer<T> csvColumnizer) throws IOException {
        writeHeader(writer);
        write(writer, items);
        writer.close();
    }

    public void createCsv(Collection<T> items, CsvColumnizer<T> csvColumnizer) throws IOException {
//        create(System.out, items);
    }

    public void createCsv(File file, Collection<T> items) throws IOException {
//        create(new PrintStream(new FileOutputStream(file)), items);
    }

    private PrintWriter createWriter(PrintStream stream) throws IOException {
        return new PrintWriter(stream);
    }

    private void writeLine(PrintWriter writer, String line) throws IOException {
        writer.append(line);
        writer.append(DEFAULT_NEWLINE);
    }

    private void writeLine(PrintWriter writer, T item) throws IOException {
        writeLine(writer, toCsv(getColumns(item)));
    }

    private void writeHeader(PrintWriter writer) throws IOException {
        Object[] headerColumns = getHeaderColumns();
        if (headerColumns.length > 0) {
            writeLine(writer, toCsv(headerColumns));
        }
    }

    private void write(PrintWriter writer, T item) throws IOException {
        writeLine(writer, item);
    }

    private void write(PrintWriter writer, Collection<T> items) throws IOException {
        for (T item : items) {
            write(writer, item);
        }
    }

    private String toCsv(Object[] columns) {
        StringBuilder builder = new StringBuilder();
        builder.append(columns[0]);
        for (int i = 1; i < columns.length; ++i) {
            builder.append(separator).append(columns[i]);
        }
        return builder.toString();
    }

    protected Object[] getHeaderColumns() {
        return new Object[]{};
    }

    protected abstract Object[] getColumns(T item);

}
