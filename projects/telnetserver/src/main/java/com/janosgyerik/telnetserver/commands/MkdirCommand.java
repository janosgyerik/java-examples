package com.janosgyerik.telnetserver.commands;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MkdirCommand extends BaseCommand {

	public MkdirCommand(File workdir) {
		super(workdir);
	}

	public List<String> execute(String... args) {
		for (String arg : args) {
			mkdir(arg);
		}
		return Arrays.asList();
	}

	private void mkdir(String path) {
		File file = path.startsWith("/") ? new File(path) : new File(execdir, path);
		file.mkdir();
	}
}
