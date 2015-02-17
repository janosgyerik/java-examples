package com.janosgyerik.examples.files.finder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DateMatchingFileFinder implements FileFinder {

    private final FileFinder fileFinder;

    public DateMatchingFileFinder(String format, Date date) {
        String pattern = new SimpleDateFormat(format).format(date);
        fileFinder = new StringMatchingFileFinder(pattern);
    }

    @Override
    public List<File> listFiles(File basedir) {
        return fileFinder.listFiles(basedir);
    }
}
