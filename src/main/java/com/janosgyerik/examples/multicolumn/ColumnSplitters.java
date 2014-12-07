package com.janosgyerik.examples.multicolumn;

public final class ColumnSplitters {
    private ColumnSplitters() {
        // utility class, forbidden constructor
    }

    public static ColumnSplitter simpleCsvSplitter() {
        return simpleCsvSplitter(",");
    }

    public static ColumnSplitter simpleCsvSplitter(String separator) {
        return new RegexColumnSplitter("\\s*" + separator + "\\s*");
    }
}
