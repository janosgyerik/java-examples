package com.janosgyerik.telnetserver.shell;

import com.janosgyerik.telnetserver.commands.Command;
import com.janosgyerik.telnetserver.commands.LsCommand;
import com.janosgyerik.telnetserver.commands.MkdirCommand;
import com.janosgyerik.telnetserver.commands.PwdCommand;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimpleShell implements Shell {
	/*
	TODO info message
Connected to localhost.
Escape character is '^]'.
^]
telnet> ls
?Invalid command
$ adadasd
bash: adadasd: command not found
	 */
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
		this(getPath(file), stdin, stdout);
	}

	private static String getPath(File file) {
		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			return file.getAbsolutePath();
		}
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
				Command command = createCommand(klass);
				for (String line : command.execute(args)) {
					writeLineOut(line);
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

	private Command createCommand(Class<? extends Command> klass) {
		try {
			Constructor<? extends Command> ctor = klass.getConstructor(File.class);
			return ctor.newInstance(cwd);
		} catch (NoSuchMethodException e) {
			// TODO better way to handle?
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		// TODO should throw something
		return null;
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
