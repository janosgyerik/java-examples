package com.janosgyerik.examples.multicolumn;

import java.util.List;

public class IntegerColumnParser extends AbstractColumnParser<Integer> {

    public IntegerColumnParser(int index) {
        super(index);
    }

    @Override
    public void parseSafeColumns(List<String> columns, int index, String value) {
        try {
            getValues().add(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            getErrors().add(new ParseError(index, value));
        }
    }
}
