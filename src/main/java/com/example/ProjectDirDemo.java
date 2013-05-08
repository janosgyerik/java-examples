package com.example;
import java.io.File;


public class ProjectDirDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new File("serialized").getAbsolutePath());
		System.out.println(new File(".").getAbsolutePath());
		//new File("serialized").mkdir();
	}

}
