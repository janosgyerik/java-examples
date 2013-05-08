package com.example;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateFormatDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date now = new Date();
		DateFormat format = new SimpleDateFormat("yyyy MMM dd HH:mm");
		System.out.println(format.format(now));
		System.out.println(new SimpleDateFormat("yyyyMMdd").format(now));
		System.out.println(new SimpleDateFormat("yyyyMMdd-HHmmss").format(now));
	}

}
