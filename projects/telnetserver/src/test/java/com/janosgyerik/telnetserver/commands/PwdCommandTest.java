package com.janosgyerik.telnetserver.commands;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class PwdCommandTest extends BaseCommandTest {

	private String pwd(File dir) {
		return new PwdCommand(dir).execute().get(0);
	}

	@Test
	public void testInWorkdir() {
		assertEquals(WORKDIR.toString(), pwd(WORKDIR));
	}

	@Test
	public void testInSubdir() {
		File dir = new File(WORKDIR, "dir1");
		assertEquals(dir.toString(), pwd(dir));
	}
}
