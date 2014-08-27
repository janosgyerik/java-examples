package com.janosgyerik.telnetserver.commands;

import org.junit.AfterClass;
import org.junit.Before;

import java.io.File;
import java.io.IOException;

public class BaseCommandTest {
	static final File WORKDIR = new File(System.getProperties().getProperty("java.io.tmpdir"), LsCommandTest.class.getSimpleName());

	protected void createTestFiles(File dir, String... filenames) throws IOException {
		for (String filename : filenames) {
			new File(dir, filename).createNewFile();
		}
	}

	protected void createTestDirs(File dir, String... dirnames) {
		for (String dirname : dirnames) {
			new File(dir, dirname).mkdirs();
		}
	}

	private static void rmRecursively(File dir) throws IOException {
		String[] items = dir.list();
		if (items != null) {
			for (String item : dir.list()) {
				File file = new File(dir, item);
				if (file.isFile()) {
					if (!file.delete()) {
						throw new IOException("Could not delete file: " + file);
					}
				} else if (file.isDirectory()) {
					rmRecursively(file);
				}
			}
		}
		if (dir.isDirectory()) {
			if (!dir.delete()) {
				throw new IOException("Could not delete dir: " + dir);
			}
		}
	}

	@Before
	public void setUp() throws IOException {
		rmRecursively(WORKDIR);
		if (!WORKDIR.mkdirs()) {
			throw new IOException("Could not create work directory: " + WORKDIR);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws IOException {
		rmRecursively(WORKDIR);
	}

}
