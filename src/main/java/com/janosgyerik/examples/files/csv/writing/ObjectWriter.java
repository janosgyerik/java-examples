package com.janosgyerik.examples.files.csv.writing;

import java.io.IOException;

public interface ObjectWriter<T> extends CsvWriter {
    /**
     * Write header to file
     */
    void writeHeader() throws IOException;

    /**
     * Append specified object on a new line to a CSV file
     *
     * @param object object to append
     */
    void appendObject(T object) throws IOException;
}
