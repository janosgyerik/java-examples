package com.janosgyerik.telnetserver.shell;

import com.janosgyerik.telnetserver.commands.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleShell implements Shell {

	private static final Logger LOGGER = Logger.getLogger(SimpleShell.class.getSimpleName());

	private static final String NAME = SimpleShell.class.getSimpleName();

	private final CommandFinder commandFinder = new SimpleCommandFinder();
	private final CommandFactory commandFactory = new SimpleCommandFactory();

	private final String homePath;
	private final InputStream stdin;
	private final OutputStream stdout;

	private File cwd;

	public SimpleShell(String homePath, InputStream stdin, OutputStream stdout) {
		this.homePath = homePath;
		this.stdin = stdin;
		this.stdout = stdout;

		this.cwd = new File(homePath);
	}

	public SimpleShell(File file, InputStream stdin, OutputStream stdout) {
		this(file.getAbsolutePath(), stdin, stdout);
	}

	@Override
	public void cd(String path) {
		File dir = path.startsWith("/") ? new File(path) : new File(cwd, path);
		if (dir.isDirectory()) {
			cwd = dir;
		}
	}

	@Override
	public void runCommand(String cmdname, String... args) {
		if (cmdname.equals("cd")) {
			cd(args.length > 0 ? args[0] : homePath);
		} else {
			Class<? extends Command> klass;
			try {
				klass = commandFinder.findCommandClassByShortName(cmdname);
			} catch (CommandFinder.NoSuchCommandException e) {
				writeLineOut(String.format("%s: %s: command not found", NAME, cmdname));
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
				return;
			}
			Command command;
			try {
				command = commandFactory.createCommand(klass, cwd);
			} catch (CommandFactory.CommandInstantiationException e) {
				writeLineOut(String.format("%s: %s: unknown error", NAME, cmdname));
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
				return;
			}
			for (String line : command.execute(args)) {
				writeLineOut(line);
			}
		}
	}

	@Override
	public void runInteractiveShell() {
		Scanner scanner = new Scanner(stdin);
		while (scanner.hasNextLine()) {
			String cmd = scanner.next();
			String[] args = scanner.nextLine().trim().split(" ");
			if (args[0].isEmpty()) {
				runCommand(cmd);
			} else {
				runCommand(cmd, args);
			}
		}
	}

	private void writeOut(String line) {
		try {
			stdout.write(line.getBytes());
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private void writeLineOut(String line) {
		writeOut(line + "\n");
	}
}
