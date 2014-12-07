package com.janosgyerik.examples.multicolumn;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractColumnParser<T> implements ColumnParser<T> {

    private final int index;

    private final List<T> values = new ArrayList<>();
    private final List<ParseError> errors = new ArrayList<>();

    public AbstractColumnParser(int index) {
        this.index = index - 1;
    }

    public abstract void parseSafeColumns(List<String> columnList, int index, String value);

    @Override
    public void parse(List<String> columns) {
        if (index < columns.size()) {
            String value = columns.get(index);
            if (value.isEmpty()) {
                return;
            }
            parseSafeColumns(columns, index, columns.get(index));
        }
    }

    @Override
    public ParsedColumn<T> getParsedColumn() {
        return new ParsedColumnImpl<>(values, errors);
    }

    public List<T> getValues() {
        return values;
    }

    public List<ParseError> getErrors() {
        return errors;
    }

}
