package com.janosgyerik.examples.files.csv;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScannerCsvReader extends AbstractCsvReader {

    private final Scanner scanner;

    public ScannerCsvReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public ScannerCsvReader(String text) {
        this(new Scanner(text));
    }

    @Override
    String nextLine() {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
