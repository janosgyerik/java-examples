package com.janosgyerik.examples.files.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SimpleCsvCreator<T> extends AbstractCsvCreator<T> {

    public SimpleCsvCreator(PrintWriter writer, String separator) throws FileNotFoundException {
        super(writer, separator);
    }

    public SimpleCsvCreator(File file, String separator) throws FileNotFoundException {
        this(new PrintWriter(file), separator);
    }

    public SimpleCsvCreator(File file) throws FileNotFoundException {
        this(file, DEFAULT_SEPARATOR);
    }

    public SimpleCsvCreator(String path) throws FileNotFoundException {
        this(new File(path));
    }

    public SimpleCsvCreator() throws FileNotFoundException {
        this(new PrintWriter(System.out), DEFAULT_SEPARATOR);
    }

    @Override
    protected Object[] getColumns(T item) {
        return new Object[0];
    }
}
