package com.janosgyerik.examples.files.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CsvReader {
    <T> List<T> readLines(RowMapper<T> mapper) throws IOException;
}
