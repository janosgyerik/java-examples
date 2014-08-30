package com.janosgyerik.telnetserver.commands;

import java.io.File;

public abstract class BaseCommand implements Command {

	protected final File execdir;

	/**
	 * Initialize command with its execution directory
	 *
	 * @param execdir the directory where the command will be executed
	 */
	public BaseCommand(File execdir) {
		this.execdir = execdir;
	}
}
