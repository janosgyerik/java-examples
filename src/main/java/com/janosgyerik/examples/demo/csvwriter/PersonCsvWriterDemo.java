package com.janosgyerik.examples.demo.csvwriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonCsvWriterDemo {

    public static void main(String[] args) throws IOException {
        PersonCsvWriter csvWriter = new PersonCsvWriter();
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("jack", 25));
        persons.add(new Person("mike", 21));
        csvWriter.createCsv(persons);
    }
}
