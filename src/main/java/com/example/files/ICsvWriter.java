package com.example.files;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public interface ICsvWriter<T> {
    void createCsv(File file, Collection<T> items) throws IOException;
}
