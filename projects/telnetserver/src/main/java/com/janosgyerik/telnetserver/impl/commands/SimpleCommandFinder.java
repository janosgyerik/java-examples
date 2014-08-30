package com.janosgyerik.telnetserver.impl.commands;

import com.janosgyerik.telnetserver.commands.Command;
import com.janosgyerik.telnetserver.commands.CommandFinder;

import java.util.HashMap;
import java.util.Map;

public class SimpleCommandFinder implements CommandFinder {
	private static final Map<String, Class<? extends Command>> commands;

	static {
		commands = new HashMap<>();
		commands.put("ls", LsCommand.class);
		commands.put("pwd", PwdCommand.class);
		commands.put("mkdir", MkdirCommand.class);
	}

	@Override
	public Class<? extends Command> findCommandClassByShortName(String shortName) throws NoSuchCommandException {
		Class<? extends Command> commandClass = commands.get(shortName);
		if (commandClass == null) {
			throw new NoSuchCommandException("No such command: " + shortName);
		}
		return commandClass;
	}
}
