package com.janosgyerik.telnetserver.commands;

public interface CommandFinder {

	/**
	 * Find a Command implementation by the commands short name
	 *
	 * @param shortName
	 * @return
	 */
	Class<? extends Command> findCommandClassByShortName(String shortName) throws NoSuchCommandException;

	class NoSuchCommandException extends Exception {
		public NoSuchCommandException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}
