package com.janosgyerik.telnetserver;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractShell implements Shell {

	final String rootPath;
	final InputStream stdin;
	final OutputStream stdout;
	final OutputStream stderr;

	public AbstractShell(String rootPath, InputStream stdin, OutputStream stdout, OutputStream stderr) {
		this.rootPath = rootPath;
		this.stdin = stdin;
		this.stdout = stdout;
		this.stderr = stderr;
	}

	@Override
	public void run(Command command, String[] args) {
//		command.execute(args);
	}
}
