package com.janosgyerik.telnetserver.shell;

public interface Shell {

	void cd(String path);

	void runCommand(String cmdname, String... args);
}
