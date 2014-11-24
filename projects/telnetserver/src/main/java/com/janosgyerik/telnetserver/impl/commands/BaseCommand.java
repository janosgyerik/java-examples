package com.janosgyerik.telnetserver.impl.commands;

import com.janosgyerik.telnetserver.commands.Command;

import java.io.File;

public abstract class BaseCommand implements Command {

    final File execdir;

    /**
     * Initialize command with its execution directory
     *
     * @param execdir the directory where the command will be executed
     */
    public BaseCommand(File execdir) {
        this.execdir = execdir;
    }

    protected File getRelativeOrAbsoluteFile(String path) {
        return path.startsWith("/") ? new File(path) : new File(execdir, path);
    }
}
