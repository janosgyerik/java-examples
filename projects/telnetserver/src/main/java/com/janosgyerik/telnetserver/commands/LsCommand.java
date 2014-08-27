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
			return execute(workdir.toString());
		} else {
			List<String> results = new ArrayList<String>();
			for (String arg : args) {
				results.addAll(execute(arg));
			}
			return results;
		}
	}

	private List<String> execute(String path) {
		File file = path.startsWith("/") ? new File(path) : new File(workdir, path);
		if (file.isFile()) {
			return Arrays.asList(path);
		} else if (file.isDirectory()) {
			String[] names = new File(path).list();
			return names != null ? Arrays.asList(names) : Arrays.<String>asList();
		}
		return Arrays.asList();
	}
}
