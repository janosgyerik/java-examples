package com.janosgyerik.examples.multicolumn;

import java.util.ArrayList;
import java.util.List;

public class MultiColumnParserImpl implements MultiColumnParser {
    @Override
    public List<ParsedColumn<?>> parse(RecordProvider recordProvider, ColumnSplitter columnSplitter, List<ColumnParser<?>> columnParserList) {
        String record;
        while ((record = recordProvider.next()) != null) {
            List<String> columns = columnSplitter.split(record);
            for (ColumnParser<?> parser : columnParserList) {
                parser.parse(columns);
            }
        }

        List<ParsedColumn<?>> parsedColumns = new ArrayList<>();
        for (ColumnParser<?> parser : columnParserList) {
            parsedColumns.add(parser.getParsedColumn());
        }
        return parsedColumns;
    }
}
