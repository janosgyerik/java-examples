package com.janosgyerik.telnetserver;

import com.janosgyerik.telnetserver.commands.Command;

public interface Shell {
	// TODO has mutable cwd
	// TODO has stdin, stdout, stderr
	// TODO runnable commands in /bin, as if PATH=/bin
	// TODO rooted in some directory
	// TODO has session: cwd
	// TODO interprets command strings

	void run(Command command, String[] args);
}
