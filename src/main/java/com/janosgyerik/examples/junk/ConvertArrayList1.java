package com.janosgyerik.examples.junk;
import java.util.ArrayList;


public class ConvertArrayList1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Object> al = new ArrayList<Object>();
		al.add("hello");
		al.add("hi");
		String[] strings = al.toArray(new String[0]);
		for (String item : strings) {
			System.out.println(item);
		}
	}

}
