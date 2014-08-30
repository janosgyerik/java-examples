package com.janosgyerik.telnetserver.commands;

import java.util.HashMap;
import java.util.Map;

public class SimpleCommandFinder implements CommandFinder {
	private static final Map<String, Class<? extends Command>> commands;

	static {
		commands = new HashMap<>();
		// TODO discover implementations of Command using reflection
		//		instead of adding manually one by one
		commands.put("ls", LsCommand.class);
		commands.put("pwd", PwdCommand.class);
		commands.put("mkdir", MkdirCommand.class);
	}

	@Override
	public Class<? extends Command> findCommandClassByShortName(String shortName) throws NoSuchCommandException {
		return commands.get(shortName);
	}
}
