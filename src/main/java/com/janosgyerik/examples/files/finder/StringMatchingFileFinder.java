package com.janosgyerik.examples.files.finder;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

public class StringMatchingFileFinder extends AbstractFileFinder {

    private final String pattern;

    public StringMatchingFileFinder(String pattern) {
        this.pattern = pattern;
    }

    @Override
    protected List<FileFilter> getFileFilters() {
        return Arrays.asList(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().contains(pattern);
            }
        });
    }
}
