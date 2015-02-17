package com.janosgyerik.examples.files.finder;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractFileFinder implements FileFinder {

    @Override
    public List<File> listFiles(File basedir) {
        File[] files = basedir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                for (FileFilter fileFilter : getFileFilters()) {
                    if (!fileFilter.accept(file)) {
                        return false;
                    }
                }
                return true;
            }
        });
        if (files == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(files);
    }

    protected abstract List<FileFilter> getFileFilters();
}
