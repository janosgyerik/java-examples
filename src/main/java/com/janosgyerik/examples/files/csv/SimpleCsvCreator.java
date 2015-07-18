package com.janosgyerik.examples.files.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;

public class SimpleCsvCreator<T> extends AbstractCsvCreator<T> {

    private final Writer writer;

    public SimpleCsvCreator(Writer writer) throws FileNotFoundException {
        this.writer = writer;
    }

    public SimpleCsvCreator(File file) throws FileNotFoundException {
        this(new PrintWriter(file));
    }

    public SimpleCsvCreator() throws FileNotFoundException {
        this(new PrintWriter(System.out));
    }

    @Override
    protected Writer getWriter() throws FileNotFoundException {
        return writer;
    }
}
