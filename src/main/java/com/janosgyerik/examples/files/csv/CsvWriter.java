package com.janosgyerik.examples.files.csv;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;

public interface CsvWriter<T> {
    void createCsv(PrintStream stream, Collection<T> items) throws IOException;
}
