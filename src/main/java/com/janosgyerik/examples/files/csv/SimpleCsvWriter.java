package com.janosgyerik.examples.files.csv;

import java.io.*;
import java.util.Collection;

public class SimpleCsvWriter {

    private static final String DEFAULT_SEPARATOR = ",";
    private static final String DEFAULT_NEWLINE = System.getProperty("line.separator");

    private final PrintWriter writer;
    private final String separator;
    private final String newline;

    public SimpleCsvWriter(PrintWriter writer, String separator) throws FileNotFoundException {
        this.writer = writer;
        this.separator = separator;
        this.newline = DEFAULT_NEWLINE;
    }

    public SimpleCsvWriter(File file, String separator) throws FileNotFoundException {
        this(new PrintWriter(file), separator);
    }

    public SimpleCsvWriter(File file) throws FileNotFoundException {
        this(file, DEFAULT_SEPARATOR);
    }

    public SimpleCsvWriter(String path) throws FileNotFoundException {
        this(new File(path));
    }

    public SimpleCsvWriter() throws FileNotFoundException {
        this(new PrintWriter(System.out), DEFAULT_SEPARATOR);
    }

    public void writeRow(Object... columns) {
        writer.write(columns[0].toString());
        for (int i = 1; i < columns.length; ++i) {
            writer.write(separator);
            writer.write(columns[i].toString());
        }
        writer.write(newline);
        writer.flush();
    }

    public void close() {
        writer.close();
    }
}
