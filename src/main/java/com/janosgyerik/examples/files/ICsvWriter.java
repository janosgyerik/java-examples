package com.janosgyerik.examples.files;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;

public interface ICsvWriter<T> {
    void createCsv(PrintStream stream, Collection<T> items) throws IOException;
}