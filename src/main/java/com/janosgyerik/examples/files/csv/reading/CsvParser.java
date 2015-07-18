package com.janosgyerik.examples.files.csv.reading;

import java.io.IOException;
import java.util.List;

public interface CsvParser {

    <T> List<T> parseLines(RowMapper<T> mapper) throws IOException;

}
