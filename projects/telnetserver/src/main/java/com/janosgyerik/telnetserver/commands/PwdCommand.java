package com.janosgyerik.telnetserver.commands;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PwdCommand extends BaseCommand {

	public PwdCommand(File workdir) {
		super(workdir);
	}

	public List<String> execute(String... args) {
		try {
			return Arrays.asList(execdir.getCanonicalPath());
		} catch (IOException e) {
			return Arrays.asList(execdir.getAbsolutePath());
		}
	}
}
