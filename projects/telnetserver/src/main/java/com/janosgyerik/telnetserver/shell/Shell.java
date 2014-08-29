package com.janosgyerik.telnetserver.shell;

public interface Shell {

	/**
	 * The "cd" builtin like in a shell
	 * @param path to change into
	 */
	void cd(String path);

	/**
	 * Run a shell command with arguments
	 * @param cmdname simple name of the command, such as "ls", "cd", "mkdir"
	 * @param args zero or more arguments of the command
	 */
	void runCommand(String cmdname, String... args);
}
