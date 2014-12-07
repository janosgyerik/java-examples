package com.janosgyerik.examples.multicolumn;

import java.util.List;

/**
 * Split input text to columns.
 * Simple implementations might assume input text = line.
 * More sophisticated implementations (for example proper CSV splitter)
 * can be made to handle embedded newlines.
 *
 * The result of splitting is always a list of String columns.
 * It's up to callers to map the String values to complex objects (if needed).
 *
 * Implementation ideas:
 *
 * - split by fixed string, for example ","
 * - split by regex, for example "\s*,\s*", and probably trim() first
 * - split based on fixed column lengths
 * - a proper csv parser/splitter could handle embedded newlines
 */
public interface ColumnSplitter {
    /**
     * Split input text to String columns.
     * Note: a proper csv parser could contain embedded newlines.
     * Note: the returned list always contains at least one item.
     *
     * @param input a non-null input String to split
     * @return columns found in the line
     */
    List<String> split(String input);
}
