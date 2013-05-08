package com.example;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SerializationDemo {

	public SerializationDemo(File out) {
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SerializationDemo demo = new SerializationDemo(new File("serialized.bin"));
		Person jack = new Person("Jack", 19);
		Person mike = new Person("Mike", 21);
		demo.save(jack);
		Set<Person> people = new HashSet<Person>();
		people.add(jack);
		people.add(mike);
		demo.save(people);
	}

	private void save(Object object) {
		if (object instanceof Serializable) {
			System.out.println("ok");
		}
		else {
			throw new IllegalArgumentException("object is not serializable: " + object);
		}
	}

}

class Person implements Serializable {
	
	private static final long serialVersionUID = 8671462899517670921L;
	
	String name;
	int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

