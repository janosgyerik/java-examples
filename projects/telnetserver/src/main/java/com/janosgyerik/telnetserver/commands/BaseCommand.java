package com.janosgyerik.telnetserver.commands;

import java.io.File;

public class BaseCommand {
	protected final File workdir;

	public BaseCommand(File workdir) {
		this.workdir = workdir;
	}
}
