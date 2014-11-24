package com.janosgyerik.examples.files.csv;

import java.io.*;

public class FileCsvReader extends AbstractCsvReader {

    private final BufferedReader reader;

    FileCsvReader(File file, String separator) throws FileNotFoundException {
        super(separator);
        reader = new BufferedReader(new FileReader(file));
    }

    public FileCsvReader(File file) throws FileNotFoundException {
        this(file, DEFAULT_SEPARATOR);
    }

    @Override
    String nextLine() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            reader.close();
        }
        return line;
    }
}
