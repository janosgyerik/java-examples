package com.janosgyerik.examples.multicolumn;

import java.util.Scanner;

public class ScannerRecordProvider implements RecordProvider {
    private final Scanner scanner;

    public ScannerRecordProvider(Scanner scanner) {
        this.scanner = scanner;
    }

    public ScannerRecordProvider(Scanner scanner, int linesToSkip) {
        this(scanner);
        for (int i = 0; i < linesToSkip; ++i) {
            next();
        }
    }

    public String next() {
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }
}
