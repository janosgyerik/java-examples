package com.janosgyerik.examples.multicolumn;

import java.util.List;

/**
 * Common interface to parse multi-column data.
 * This is different from a parser where each record is mapped to an object.
 * Here, the desired output is a collection of columns as independent units,
 * each record contributing to multiple columns.
 *
 * A column may use data from one or more other columns to derive its value,
 * for example:
 * - use an ID from the first column
 * - combine multiple columns to form a date
 *
 * The details are irrelevant: it's the job of a ColumnSplitter
 * to split the input to columns correctly.
 *
 * Example use cases:
 *
 * + Curve matrix: multiple columns, type double, using duration from another column
 * + Common id column: TODO: find a practical example
 * - Date column derived from year + month + day columns
 * - Multiple types: TODO: find a practical example
 * x Named columns: columns can be in any order, reader can find the right column by name in header
 * + Missing values: omit or fill with null? -> omit
 * + Invalid rows: ignore invalid rows. The header should not be treated as invalid
 * + Picking arbitrary columns: take only column 2 and 4, ignore the rest
 */
public interface MultiColumnParser {

    /**
     * Parse all data coming from a record provider, splitting to columns,
     * and using column parsers to build columns.
     *
     * @param provider record provider
     * @param splitter column splitter to split records to columns
     * @param parsers list of parsers to parse the columns
     * @return parsed columns
     */
    List<ParsedColumn<?>> parse(RecordProvider provider, ColumnSplitter splitter, List<ColumnParser<?>> parsers);

}
