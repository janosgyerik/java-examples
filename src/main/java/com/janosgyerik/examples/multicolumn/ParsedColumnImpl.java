package com.janosgyerik.examples.multicolumn;

import java.util.List;

public class ParsedColumnImpl<T> implements ParsedColumn<T> {
    private final List<T> values;
    private final List<ParseError> errors;

    public ParsedColumnImpl(List<T> values, List<ParseError> errors) {
        this.values = values;
        this.errors = errors;
    }

    @Override
    public List<T> getValues() {
        return values;
    }

    @Override
    public List<ParseError> getParseErrors() {
        return errors;
    }
}
