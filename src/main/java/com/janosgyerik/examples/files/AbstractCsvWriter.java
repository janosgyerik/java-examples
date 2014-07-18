package com.janosgyerik.examples.files;

import java.io.*;
import java.util.Collection;

public abstract class AbstractCsvWriter<T> implements CsvWriter<T> {

    private static final String DEFAULT_NEWLINE = System.getProperty("line.separator");
    private static final String DEFAULT_SEPARATOR = ",";

    private final String separator;

    public AbstractCsvWriter() {
        this(DEFAULT_SEPARATOR);
    }

    AbstractCsvWriter(String separator) {
        this.separator = separator;
    }

    @Override
    public void createCsv(PrintStream stream, Collection<T> items) throws IOException {
        PrintWriter writer = createWriter(stream);
        writeHeader(writer);
        write(writer, items);
        writer.close();
    }

    public void createCsv(Collection<T> items) throws IOException {
        createCsv(System.out, items);
    }

    public void createCsv(File file, Collection<T> items) throws IOException {
        createCsv(new PrintStream(new FileOutputStream(file)), items);
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
