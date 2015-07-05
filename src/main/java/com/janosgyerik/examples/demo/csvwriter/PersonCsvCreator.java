package com.janosgyerik.examples.demo.csvwriter;

import com.janosgyerik.examples.demo.company.Person;
import com.janosgyerik.examples.files.csv.AbstractCsvCreator;

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
}
