package com.janosgyerik.telnetserver.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LsCommand extends BaseCommand {

	public LsCommand(File workdir) {
		super(workdir);
	}

	public List<String> execute(String... args) {
		if (args.length == 0) {
			return ls(execdir.toString());
		} else {
			List<String> results = new ArrayList<>();
			for (String arg : args) {
				results.addAll(ls(arg));
			}
			return results;
		}
	}

	private List<String> ls(String path) {
		File file = path.startsWith("/") ? new File(path) : new File(execdir, path);
		if (file.isFile()) {
			return Arrays.asList(path);
		} else if (file.isDirectory()) {
			String[] names = new File(path).list();
			return names != null ? Arrays.asList(names) : Arrays.<String>asList();
		}
		return Arrays.asList();
	}
}
