package com.janosgyerik.examples.demo.csv.reading;

import com.janosgyerik.examples.demo.company.Person;
import com.janosgyerik.examples.files.csv.reading.CsvParser;
import com.janosgyerik.examples.files.csv.reading.FileCsvParser;
import com.janosgyerik.examples.files.csv.reading.RowMapper;

import java.io.File;
import java.io.IOException;

public class PersonCsvReaderDemo {
    public static void main(String[] args) throws IOException {
        CsvParser reader = new FileCsvParser(new File("/tmp/file.csv"));
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
        for (Person person : reader.parseLines(mapper)) {
            System.out.println(person);
        }
    }
}
