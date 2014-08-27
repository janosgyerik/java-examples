package com.janosgyerik.telnetserver.commands;

import java.util.List;

public interface Command {
	/**
	 * Execute command with 0 or more arguments, return output lines as a list
	 *
	 * @param args arguments, can be omitted
	 * @return output lines as a list
	 */
	public List<String> execute(String... args);
}
