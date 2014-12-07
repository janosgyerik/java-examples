package com.janosgyerik.examples.multicolumn;

import java.util.Arrays;
import java.util.List;

public class RegexColumnSplitter implements ColumnSplitter {

    private final String regex;

    public RegexColumnSplitter(String regex) {
        this.regex = regex;
    }

    @Override
    public List<String> split(String input) {
        return Arrays.asList(input.trim().split(regex));
    }
}
