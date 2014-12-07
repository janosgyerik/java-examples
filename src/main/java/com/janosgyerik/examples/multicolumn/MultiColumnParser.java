package com.janosgyerik.examples.multicolumn;

import java.util.List;

/**
 * Common interface to parse multi-column data.
 * This is different from a parser where each record is mapped to an object.
 * Here, the desired output is a collection of columns as independent units,
 * each record contributing to multiple columns.
 *
 * A column may use data from one or more other columns to derive its value,
 * for example use an ID from the first column,
 * or combine multiple columns to form a date.
 *
 * Input is expected *record by record*, where a record may be simply a line
 * delimited by commas, or it may be a proper CSV record with embedded
 * newlines. Or it may be a line with fixed-width columns.
 * The details are irrelevant: it's the job of a ColumnSplitter
 * to split the input to columns correctly.
 *
 * The input may come from anywhere. It can be lines from a file,
 * or a scanner, or stream.
 *
 * Responsibilities of callers:
 * - use a ColumnSplitter implementation appropriate for the data
 * - provide input record by record, from whatever source
 *
 * Collaborators:
 * - a ColumnSplitter to split lines to columns
 * - a collection of ColumnParsers to parse columns -> constructor params, final
 * - a collection of ColumnAccumulators to accumulate parsed columns -> initialized in constructor
 * - a Result object containing Columns and diagnostic information about parsing errors
 * - Column<T> representing a column of specific type
 *
 * Questions:
 * - who should collect the errors? each column its own errors, or the parser, of all errors?
 *      - when reporting, it might be more readable to see errors grouped by column
 *      - if the parser collects, the natural grouping is most likely by row
 *
 * Processing logic:
 * - while nextRecord is not null
 *      - instead of hasNextLine style, this naturally ensures that collaborators never receive a null line
 * - split
 * - for each column parser:
 *      - parse row: pick up the interesting column, possibly combining other columns
 *          - record data
 *          - record errors
 * - no more lines:
 *      - build result object from parsed columns
 *
 * Example cases to support:
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

    /*
    + the parser should build multiple columns
    + there may be parsing errors, which would be nice to be accessible
        + index that failed
        + value that was found and couldn't be parsed
    + some files may have 2 columns, others 9
    + sometimes we might want only specific columns, say 2, 3, 6 and ignore the rest
    + sometimes we might want only specific columns, say 3-6 and ignore the rest
        + a wrapper class could do it
    + optional: named columns, variable
        + a wrapper class could do it
    x it can be desirable to detect headers, so that columns can be in any order
        - or perhaps spec owners should have the power to dictate a canonical order?
        - ^^^ definitely. But is it possible to implement in a way to leave the option open?
    x named columns
        - use header if exists
        - use x1, x2, x3, ...
        - use user-defined
     */
    List<ParsedColumn<?>> parse(RecordProvider recordProvider, ColumnSplitter columnSplitter, List<ColumnParser<?>> columnParserList);

}
