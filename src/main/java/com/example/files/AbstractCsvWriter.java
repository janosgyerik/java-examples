package com.example.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractCsvWriter<T> implements ICsvWriter<T> {

    private static final String DEFAULT_SEPARATOR = ",";

    private final String separator;

    AbstractCsvWriter() {
        this(DEFAULT_SEPARATOR);
    }

    AbstractCsvWriter(String separator) {
        this.separator = separator;
    }

    @Override
    public void createCsv(File file, Collection<T> items) throws IOException {
        BufferedWriter writer = createWriter(file);
        writeHeader(writer);
        write(writer, items);
        writer.close();
    }

    private BufferedWriter createWriter(File file) throws IOException {
        return new BufferedWriter(new FileWriter(file));
    }

    private void writeLine(BufferedWriter writer, String line) throws IOException {
        writer.append(line);
        writer.newLine();
    }

    private void writeLine(BufferedWriter writer, T item) throws IOException {
        writeLine(writer, toCsv(getColumns(item)));
    }

    private void writeHeader(BufferedWriter writer) throws IOException {
        List<Object> headerColumns = getHeaderColumns();
        if (!headerColumns.isEmpty()) {
            writeLine(writer, toCsv(headerColumns));
        }
    }

    private void write(BufferedWriter writer, T item) throws IOException {
        writeLine(writer, item);
    }

    private void write(BufferedWriter writer, Collection<T> items) throws IOException {
        for (T item : items) {
            write(writer, item);
        }
    }

    private String toCsv(List<Object> columns) {
        StringBuilder builder = new StringBuilder();
        Iterator<Object> iter = columns.iterator();
        builder.append(iter.next());
        while (iter.hasNext()) {
            builder.append(separator).append(iter.next());
        }
        return builder.toString();
    }

    protected List<Object> getHeaderColumns() {
        return Collections.emptyList();
    }

    protected abstract List<Object> getColumns(T item);
}
