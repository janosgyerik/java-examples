package com.janosgyerik.examples.files.csv;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScannerCsvParser extends AbstractCsvParser {

    private final Scanner scanner;

    public ScannerCsvParser(Scanner scanner) {
        this.scanner = scanner;
    }

    public ScannerCsvParser(String text) {
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
