package com.janosgyerik.examples.demo.csvwriter;

import com.janosgyerik.examples.files.AbstractCsvWriter;

public class PersonCsvWriter extends AbstractCsvWriter<Person> {

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
