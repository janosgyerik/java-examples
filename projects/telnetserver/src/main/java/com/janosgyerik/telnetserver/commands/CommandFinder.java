package com.janosgyerik.telnetserver.commands;

public interface CommandFinder {

    /**
     * Find a Command implementation by the command's short name
     *
     * @param shortName the short name of the command, such as "ls", "pwd"
     * @return the class that implements the command
     */
    Class<? extends Command> findCommandClassByShortName(String shortName) throws NoSuchCommandException;

    class NoSuchCommandException extends Exception {
        public NoSuchCommandException(String message) {
            super(message);
        }
    }
}
