package com.janosgyerik.telnetserver.shell;

import com.janosgyerik.telnetserver.impl.shell.SimpleShell;
import com.janosgyerik.telnetserver.util.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

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

	private Shell createShell(OutputStream stdout) {
		return new SimpleShell(WORKDIR.getAbsolutePath(), null, stdout);
	}

	private ByteArrayOutputStream createMockStdout() {
		return mock(ByteArrayOutputStream.class);
	}

	private void verifyLineWritten(ByteArrayOutputStream stream, String line) throws IOException {
		verifyLineWritten(stream, line, 1);
	}

	private void verifyLineWritten(ByteArrayOutputStream stream, String line, int count) throws IOException {
		verify(stream, times(count)).write((line + "\n").getBytes());
	}

	private void verifyAbsolutePathWritten(ByteArrayOutputStream stream, File file) throws IOException {
		verifyAbsolutePathWritten(stream, file, 1);
	}

	private void verifyAbsolutePathWritten(ByteArrayOutputStream stream, File file, int count) throws IOException {
		verifyLineWritten(stream, file.getCanonicalPath(), count);
	}

	@Test
	public void testInitialDir() throws IOException {
		ByteArrayOutputStream stdout = createMockStdout();
		Shell shell = createShell(stdout);
		shell.runCommand("pwd");
		verifyAbsolutePathWritten(stdout, WORKDIR);
		verifyNoMoreInteractions(stdout);
	}

	@Test
	public void testCannotCdToNonexistent() throws IOException {
		ByteArrayOutputStream stdout = createMockStdout();
		Shell shell = createShell(stdout);

		shell.runCommand("pwd");
		verifyAbsolutePathWritten(stdout, WORKDIR);

		shell.cd("nonexistent");
		shell.runCommand("pwd");
		verifyAbsolutePathWritten(stdout, WORKDIR, 2);

		verifyNoMoreInteractions(stdout);
	}

	@Test
	public void testChangingDirWithRelpath() throws IOException {
		ByteArrayOutputStream stdout = createMockStdout();
		Shell shell = createShell(stdout);

		shell.runCommand("pwd");
		verifyAbsolutePathWritten(stdout, WORKDIR);

		String dirname = "dir1";
		shell.runCommand("mkdir", dirname);

		shell.cd(dirname);
		shell.runCommand("pwd");
		verifyAbsolutePathWritten(stdout, new File(WORKDIR, "dir1"));

		verifyNoMoreInteractions(stdout);
	}

	@Test
	public void testChangingDirWithAbsolutePath() throws IOException {
		ByteArrayOutputStream stdout = createMockStdout();
		Shell shell = createShell(stdout);

		shell.runCommand("pwd");
		verifyAbsolutePathWritten(stdout, WORKDIR);

		String dirname = "dir1";
		shell.runCommand("mkdir", dirname);

		File dir = new File(WORKDIR, dirname);
		shell.cd(dir.getAbsolutePath());
		shell.runCommand("pwd");
		verifyAbsolutePathWritten(stdout, dir);

		verifyNoMoreInteractions(stdout);
	}

	@Test
	public void testCdUp() throws IOException {
		ByteArrayOutputStream stdout = createMockStdout();
		Shell shell = createShell(stdout);

		// sanity check, to be sure there's a parent to go up
		assertTrue(WORKDIR.getAbsolutePath().matches("/.*/.*"));

		String dirname = "..";
		shell.cd(dirname);
		shell.runCommand("pwd");
		verifyAbsolutePathWritten(stdout, new File(WORKDIR, dirname));

		verifyNoMoreInteractions(stdout);
	}
}
