package com.janosgyerik.examples.files.csv;

import java.io.IOException;
import java.util.List;

public interface CsvParser {

    <T> List<T> readLines(RowMapper<T> mapper) throws IOException;

}
