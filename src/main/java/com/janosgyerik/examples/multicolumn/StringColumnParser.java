package com.janosgyerik.examples.multicolumn;

import java.util.List;

public class StringColumnParser extends AbstractColumnParser<String> {

    public StringColumnParser(int index) {
        super(index);
    }

    @Override
    public void parseSafeColumns(List<String> columns, int index, String value) {
        getValues().add(value);
    }
}
