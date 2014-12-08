package com.janosgyerik.examples.multicolumn;

public interface RecordProvider {
    /**
     * Return the next record as a String.
     * Return null if there are no more records.
     *
     * A record may be for example:
     * - a line delimited by commas
     * - a proper CSV record with embedded newlines
     * - a line with fixed-width columns
     *
     * Make sure to use a right ColumnSplitter that can handle the records.
     *
     * @return the next record or null
     */
    String next();
}
