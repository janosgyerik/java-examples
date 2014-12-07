package com.janosgyerik.examples.multicolumn;

import java.util.List;

public interface ColumnParser<T> {

    /**
     * Parse value, using one or more columns.
     * Accumulate the value and any errors inside the implementation,
     * to be able to return with getParsedColumn()
     *
     * @param columns of a data record
     */
    void parse(List<String> columns);

    /**
     * Get accumulated parsed values and errors.
     *
     * @return ParsedColumn object
     */
    ParsedColumn<T> getParsedColumn();
}
