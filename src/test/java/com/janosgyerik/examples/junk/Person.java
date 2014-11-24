package com.janosgyerik.examples.junk;


public class Person {

    final String name;
    final int age;

    // sub-classes need a default constructor...
    public Person() {
        name = null;
        age = -1;  // invalid
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
