package com.janosgyerik.telnetserver.commands;

import com.janosgyerik.telnetserver.util.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;

import java.io.File;
import java.io.IOException;

public class BaseCommandTest {
	static final File WORKDIR = new File(System.getProperties().getProperty("java.io.tmpdir"), LsCommandTest.class.getSimpleName());

	@Before
	public void setUp() throws IOException {
		FileUtils.setupCleanDir(WORKDIR);
	}

	@AfterClass
	public static void tearDownAfterClass() throws IOException {
		FileUtils.wipeDirRecursively(WORKDIR);
	}
}
