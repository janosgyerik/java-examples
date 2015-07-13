package com.janosgyerik.examples.demo.csvwriter;

import com.janosgyerik.examples.demo.company.Person;
import com.janosgyerik.examples.files.csv.AbstractCsvCreator;
import com.janosgyerik.examples.files.csv.CsvColumnizer;

import java.io.IOException;
import java.util.Collection;

public class PersonCsvCreator extends AbstractCsvCreator<Person> {

    int counter = 0;

    @Override
    protected Object[] getHeaderColumns() {
        return new Object[]{"id", "name", "age"};
    }

    @Override
    protected Object[] getColumns(Person item) {
        return new Object[]{++counter, item.name, item.age};
    }

    @Override
    public void create(Collection<Person> items, CsvColumnizer<Person> csvColumnizer) throws IOException {

    }
}
