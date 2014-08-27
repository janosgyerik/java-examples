package com.janosgyerik.telnetserver.commands;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LsTest {
	private static final File WORKDIR = new File(System.getProperties().getProperty("java.io.tmpdir"), LsTest.class.getSimpleName());

	private List<String> ls() {
		return new LsCommand(WORKDIR).execute();
	}

	private List<String> ls(String... paths) {
		return new LsCommand(WORKDIR).execute(paths);
	}

	private List<String> ls(File file) {
		return new LsCommand(WORKDIR).execute(file.toString());
	}

	private void createTestFiles(File dir, String... filenames) throws IOException {
		for (String filename : filenames) {
			new File(dir, filename).createNewFile();
		}
	}

	private void createTestDirs(File dir, String... dirnames) {
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

	@Test
	public void testEmptyDir() {
		assertTrue(ls().isEmpty());
	}

	@Test
	public void testNonexistentDir() {
		assertTrue(ls(new File(WORKDIR, "nonexistent")).isEmpty());
	}

	@Test
	public void testDirWithFiles() throws IOException {
		String[] filenames = new String[]{"file1.txt", "file2.txt"};
		createTestFiles(WORKDIR, filenames);
		assertEquals(Arrays.asList(filenames), ls());
	}

	@Test
	public void testDirWithFilesAndDirs() throws IOException {
		String[] filenames = new String[]{"file1.txt", "file2.txt"};
		createTestFiles(WORKDIR, filenames);
		assertEquals(Arrays.asList(filenames), ls());

		String[] dirnames = new String[]{"dir1", "dir2"};
		createTestDirs(WORKDIR, dirnames);
		assertNotEquals(Arrays.asList(filenames), ls());

		List<String> names = new ArrayList<String>();
		names.addAll(Arrays.asList(dirnames));
		names.addAll(Arrays.asList(filenames));
		assertEquals(names, ls());

		String[] filenames2 = new String[]{"file3.txt", "file4.txt"};
		createTestFiles(new File(WORKDIR, dirnames[0]), filenames2);
		assertEquals(Arrays.asList(filenames2), ls(new File(WORKDIR, dirnames[0])));
	}

	@Test
	public void testWithFileRelpath() throws IOException {
		String[] filenames = new String[]{"file1.txt", "file2.txt"};
		createTestFiles(WORKDIR, filenames);
		assertEquals(Arrays.asList(filenames[0]), ls(new File(filenames[0])));
	}

	@Test
	public void testWithFileAbsolutePath() throws IOException {
		String[] filenames = new String[]{"file1.txt", "file2.txt"};
		createTestFiles(WORKDIR, filenames);
		assertEquals(Arrays.asList(new File(WORKDIR, filenames[0]).toString()), ls(new File(WORKDIR, filenames[0])));
	}

	@Test
	public void testWithNoArgs() throws IOException {
		String[] filenames = new String[]{"file1.txt", "file2.txt"};
		createTestFiles(WORKDIR, filenames);
		assertEquals(Arrays.asList(filenames), ls());
	}

	@Test
	public void testWithManyArgs() throws IOException {
		String[] filenames = new String[]{"file1.txt"};
		createTestFiles(WORKDIR, filenames);
		String[] args = new String[]{filenames[0], filenames[0]};
		assertEquals(Arrays.asList(args), ls(args));
	}
}
