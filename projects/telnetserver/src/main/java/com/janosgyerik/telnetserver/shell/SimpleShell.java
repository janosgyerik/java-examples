package com.janosgyerik.telnetserver.shell;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class SimpleShell implements Shell {
	/*
	TODO info message
Connected to localhost.
Escape character is '^]'.
^]
telnet> ls
?Invalid command
$ adadasd
bash: adadasd: command not found
	 */
	// TODO logout with Control-D
	// TODO motd

	final String rootPath;
	final InputStream stdin;
	final OutputStream stdout;

	private File cwd;

	public SimpleShell(String rootPath, InputStream stdin, OutputStream stdout) {
		this.rootPath = rootPath;
		this.stdin = stdin;
		this.stdout = stdout;

		this.cwd = new File(rootPath);
	}

	@Override
	public void cd(String path) {
		File dir = new File(path);
		if (dir.isDirectory()) {
			cwd = dir;
		}
	}

	@Override
	public void runCommand(String cmdname, String[] args) {

	}
}
