package com.janosgyerik.examples.files.csv;

import java.io.IOException;
import java.util.Collection;

/**
 * Create a CSV file from a collection of objects of type T
 *
 * @param <T> type of objects
 */
public interface CsvCreator<T> {

    /**
     * Create a CSV file from a collection of objects, and close the file
     *
     * @param items objects to write to CSV
     * @throws IOException
     */
    void create(Collection<T> items, CsvColumnizer<T> csvColumnizer) throws IOException;

}
