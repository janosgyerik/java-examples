package com.janosgyerik.examples.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileComparator {

    public boolean sameContentSimple(File file1, File file2) {
        try {
            return sameContent(file1, file2);
        } catch (IOException e) {
            return false;
        }
    }

    public boolean sameContent(File file1, File file2) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));
        String line1, line2;
        while (true) {
            line1 = reader1.readLine();
            line2 = reader2.readLine();
            if (line1 == null || line2 == null || !line1.equals(line2)) {
                break;
            }
        }
        reader1.close();
        reader2.close();

        return line1 == null && line2 == null;
    }

}
