package com.janosgyerik.examples.multicolumn;

import java.util.Arrays;
import java.util.List;

public class RegexColumnSplitter implements ColumnSplitter {

    private final String regex;

    public RegexColumnSplitter(String regex) {
        this.regex = String.format("\\s*%s\\s*", regex);
    }

    @Override
    public List<String> splitLine(String line) {
        return Arrays.asList(line.trim().split(regex));
    }
}
