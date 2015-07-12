package com.janosgyerik.examples.files.csv;

public interface CsvColumnizer<T> {

    /**
     * Get the column headers
     *
     * @return array of objects to use as the column headers
     */
    Object[] getColumnHeaders();

    /**
     * Get the column values of specified object
     *
     * @param item the object from which to extract column values
     * @return array of objects to use as the column values
     */
    Object[] getColumnValues(T item);
}
