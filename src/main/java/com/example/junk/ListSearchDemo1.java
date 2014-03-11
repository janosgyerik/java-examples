package com.example.junk;
import java.util.ArrayList;
import java.util.List;


public class ListSearchDemo1 {
	
	class Person {
		String name;
		public Person(String name) {
			this.name = name;
		}
		public String toString() {
			return name;
		}
		public boolean equals(Object obj) {
			if (obj instanceof Person) {
				Person other = (Person)obj;
				if (name.equals(other.name)) {
					return true;
				}
			}
			else if (obj instanceof String) {
				System.out.println("compare string: " + name + " vs " + obj);
				if (name.equals(obj)) {
					return true;
				}
			}
			return false;
		}
	}
	
	public void test1() {
		List<Person> people = new ArrayList<Person>();
		people.add(new Person("Jack"));
		people.add(new Person("Mike"));
		for (Person person : people) {
			System.out.println(person);
			if (person.equals(new Person("Jack"))) {
				System.out.println("jack");
			}
		}
		if (people.contains(new Person("Jack"))) {
			System.out.println("contains Jack");
		}
		if (people.contains("Mike")) {
			System.out.println("contains string");
		}
		System.out.println("Pete equals? " + new Person("Pete").equals("Pete"));
	}

	public static void main(String[] args) {
		ListSearchDemo1 demo = new ListSearchDemo1();
		demo.test1();
	}
}
