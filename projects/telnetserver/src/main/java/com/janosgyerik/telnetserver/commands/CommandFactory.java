package com.janosgyerik.telnetserver.commands;

import java.io.File;

public interface CommandFactory {

	/**
	 * Construct a command object with execution directory
	 *
	 * @param klass the command class to instantiate
	 * @param execdir the execution directory,
	 *                to be passed to the constructor of the command class
	 * @return a command object implementing Command, ready to execute
	 */
	Command createCommand(Class<? extends Command> klass, File execdir) throws CommandInstantiationException;

	class CommandInstantiationException extends Exception {
		public CommandInstantiationException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}
