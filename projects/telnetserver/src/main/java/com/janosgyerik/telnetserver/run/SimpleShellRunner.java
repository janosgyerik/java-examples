package com.janosgyerik.telnetserver.run;

import com.janosgyerik.telnetserver.shell.SimpleShell;

import java.io.File;
import java.io.IOException;

public class SimpleShellRunner {
	public static void main(String[] args) throws IOException {
		new SimpleShell(new File(".").getCanonicalPath(), System.in, System.out).runInteractiveShell();
	}
}
