package com.janosgyerik.telnetserver.shell;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class BaseShell implements Shell {

	final String rootPath;
	final InputStream stdin;
	final OutputStream stdout;
	final OutputStream stderr;

	public BaseShell(String rootPath, InputStream stdin, OutputStream stdout, OutputStream stderr) {
		this.rootPath = rootPath;
		this.stdin = stdin;
		this.stdout = stdout;
		this.stderr = stderr;
	}
}
