package com.janosgyerik.examples.multicolumn;

import java.util.List;

/**
 * A column of specific type that was parsed by some parser.
 *
 * @param <T>
 */
public interface ParsedColumn<T> {
    /**
     * Get the list of parsed values
     *
     * @return list of parsed values
     */
    List<T> getValues();

    /**
     * Get the list of errors that occurred during parsing
     *
     * @return list of errors
     */
    List<ParseError> getParseErrors();
}
