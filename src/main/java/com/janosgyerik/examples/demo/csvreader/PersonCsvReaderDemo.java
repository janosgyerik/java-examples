package com.janosgyerik.examples.demo.csvreader;

import com.janosgyerik.examples.demo.company.Person;
import com.janosgyerik.examples.files.csv.CsvReader;
import com.janosgyerik.examples.files.csv.FileCsvReader;
import com.janosgyerik.examples.files.csv.RowMapper;

import java.io.File;
import java.io.IOException;

public class PersonCsvReaderDemo {
    public static void main(String[] args) throws IOException {
        CsvReader reader = new FileCsvReader(new File("/tmp/file.csv"));
        RowMapper<Person> mapper = new RowMapper<Person>() {
            @Override
            public Person mapRow(String[] cols) {
                return new Person(cols[1], Integer.parseInt(cols[2]));
            }

            @Override
            public boolean isValidRow(String[] cols) {
                return cols.length == 3 && cols[2].matches("^\\d+$");
            }
        };
        for (Person person : reader.readLines(mapper)) {
            System.out.println(person);
        }
    }
}
