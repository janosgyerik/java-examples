package com.janosgyerik.examples.files.finder;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class RegexMatchingFileFinder extends AbstractFileFinder {

    private final Pattern pattern;

    public RegexMatchingFileFinder(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    protected List<FileFilter> getFileFilters() {
        return Arrays.asList(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return pattern.matcher(file.getName()).find();
            }
        });
    }

}
