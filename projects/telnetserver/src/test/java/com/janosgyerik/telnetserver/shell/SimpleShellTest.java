package com.janosgyerik.telnetserver.shell;

import com.janosgyerik.telnetserver.util.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SimpleShellTest {
	static final File WORKDIR = new File(System.getProperties().getProperty("java.io.tmpdir"), SimpleShellTest.class.getSimpleName());

	@Before
	public void setUp() throws IOException {
		FileUtils.setupCleanDir(WORKDIR);
	}

	@AfterClass
	public static void tearDownAfterClass() throws IOException {
		FileUtils.wipeDirRecursively(WORKDIR);
	}

	@Test
	public void testInitialDir() throws IOException {
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		Shell shell = new SimpleShell(WORKDIR.getAbsolutePath(), null, stdout);
		shell.runCommand("pwd");
		assertEquals(WORKDIR.getAbsolutePath() + "\n", new String(stdout.toByteArray()));
		// TODO verify the line written
	}

//	@Test
	public void testChangingDirWithRelpath() {
		Shell shell = new SimpleShell(WORKDIR.getAbsolutePath(), null, null);
		shell.runCommand("pwd");
		// TODO verify the line written
		shell.runCommand("mkdir", "dir1");
		shell.cd("dir1");
		shell.runCommand("pwd");
		// TODO verify the line written
	}

	// TODO create shell in some root dir
	// TODO run some commands
	// TODO test cd
	// TODO confirm command output on stdout
	// TODO restrict shell to root dir if not too ugly
}
