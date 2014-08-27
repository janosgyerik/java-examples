package com.janosgyerik.telnetserver.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PwdCommand extends BaseCommand {
	public PwdCommand(File workdir) {
		super(workdir);
	}

	public List<String> execute(String... args) {
		return Arrays.asList(workdir.getAbsolutePath());
	}
}
