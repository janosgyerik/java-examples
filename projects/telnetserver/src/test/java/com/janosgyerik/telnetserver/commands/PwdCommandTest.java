package com.janosgyerik.telnetserver.commands;

import com.janosgyerik.telnetserver.impl.commands.PwdCommand;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PwdCommandTest extends BaseCommandTest {

	private String pwd(File dir, String... args) {
		return new PwdCommand(dir).execute(args).get(0);
	}

	@Test
	public void testInWorkdir() throws IOException {
		assertEquals(WORKDIR.getCanonicalPath(), pwd(WORKDIR));
	}

	@Test
	public void testInSubdir() throws IOException {
		File dir = new File(WORKDIR, "dir1");
		assertEquals(dir.getCanonicalPath(), pwd(dir));
	}

	@Test
	public void testInParentdir() throws IOException {
		File dir = new File(WORKDIR, "..");
		assertEquals(dir.getCanonicalPath(), pwd(dir));
	}

	@Test
	public void testWithArgs() throws IOException {
		assertEquals(WORKDIR.getCanonicalPath(), pwd(WORKDIR, "unused"));
	}
}
