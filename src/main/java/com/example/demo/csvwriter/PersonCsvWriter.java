package com.example.demo.csvwriter;

import com.example.files.AbstractCsvWriter;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PersonCsvWriter extends AbstractCsvWriter<Person> {

    int counter = 0;

    public static void main(String[] args) throws IOException {
        PersonCsvWriter csvWriter = new PersonCsvWriter();
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("jack", 25));
        persons.add(new Person("mike", 21));
        csvWriter.createCsv(new File("sample.csv"), persons);
    }

    @Override
    protected List<Object> getHeaderColumns() {
        return Arrays.asList(new Object[]{"id", "name", "age"});
    }

    @Override
    protected List<Object> getColumns(Person item) {
        return Arrays.asList(new Object[]{++counter, item.name, item.age});
    }
}
