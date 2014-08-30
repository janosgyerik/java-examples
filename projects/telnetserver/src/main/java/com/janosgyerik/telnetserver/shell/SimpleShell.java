package com.janosgyerik.telnetserver.shell;

import com.janosgyerik.telnetserver.commands.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimpleShell implements Shell {
	// TODO logout with Control-D
	// TODO motd

	final String homePath;
	final InputStream stdin;
	final OutputStream stdout;

	private File cwd;

	private static final Map<String, Class<? extends Command>> commands;

	static {
		commands = new HashMap<String, Class<? extends Command>>();
		// TODO discover implementations of Command using reflection
		//		instead of adding manually one by one
		commands.put("ls", LsCommand.class);
		commands.put("pwd", PwdCommand.class);
		commands.put("mkdir", MkdirCommand.class);
	}

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
			Class<? extends Command> klass = commands.get(cmdname);
			if (klass == null) {
				writeOut(String.format("%s: %s: command not found\n", SimpleShell.class.getSimpleName(), cmdname));
			} else {
				try {
					Command command = new SimpleCommandFactory().createCommand(klass, cwd);
					for (String line : command.execute(args)) {
						writeLineOut(line);
					}
				} catch (CommandFactory.CommandInstantiationException e) {
					e.printStackTrace();
				}
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
			// TODO better way to handle?
			e.printStackTrace();
		}
	}

	private void writeLineOut(String line) {
		writeOut(line + "\n");
	}
}
