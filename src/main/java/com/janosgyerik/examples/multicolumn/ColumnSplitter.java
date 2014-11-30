package com.janosgyerik.examples.multicolumn;

import java.util.List;

/**
 * Split a line to columns. Implementation ideas:
 *
 * - split by fixed string, for example ","
 * - split by regex, for example "\s*,\s*", and probably trim() first
 * - split based on fixed column lengths
 * - a proper csv parser/splitter could handle embedded newlines
 */
public interface ColumnSplitter {
    /**
     * Split a "line" of input to String columns.
     * Note: a proper csv parser could contain embedded newlines.
     * Note: the returned array always contains at least one entry.
     *
     * @param line a non-null input String to split
     * @return columns found in the line
     */
    List<String> splitLine(String line);
}
