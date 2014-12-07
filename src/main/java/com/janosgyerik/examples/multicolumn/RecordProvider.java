package com.janosgyerik.examples.multicolumn;

public interface RecordProvider {
    /**
     * Return the next record as a String,
     * or null if there are no more records.
     *
     * @return the next record or null
     */
    String next();
}
