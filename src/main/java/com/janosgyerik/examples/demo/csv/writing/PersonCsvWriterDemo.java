package com.janosgyerik.examples.demo.csv.writing;

import com.janosgyerik.examples.demo.company.Person;
import com.janosgyerik.examples.files.csv.writing.CsvColumnizer;
import com.janosgyerik.examples.files.csv.writing.CsvCreator;
import com.janosgyerik.examples.files.csv.writing.SimpleCsvCreator;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PersonCsvWriterDemo {

    public static void main(String[] args) throws IOException {
        CsvCreator<Person> creator = new SimpleCsvCreator<>();
        CsvColumnizer<Person> columnizer = new CsvColumnizer<Person>() {
            @Override
            public Object[] getColumnHeaders() {
                return new Object[] {
                        "name",
                        "age",
                };
            }

            @Override
            public Object[] getColumnValues(Person item) {
                return new Object[] {
                        item.name,
                        item.age,
                };
            }
        };
        List<Person> persons = Arrays.asList(
                new Person("Jack", 32),
                new Person("Mike", 25)
        );
        creator.create(persons, columnizer);
    }
}
