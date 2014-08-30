package com.janosgyerik.telnetserver.impl.server;

import com.janosgyerik.telnetserver.shell.Shell;
import com.janosgyerik.telnetserver.impl.shell.SimpleShell;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientProxy implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(ClientProxy.class.getSimpleName());

	private final Shell shell;
	private final InputStream stdin;
	private final OutputStream stdout;

	public ClientProxy(Socket socket) throws IOException {
		stdin = socket.getInputStream();
		stdout = socket.getOutputStream();
		shell = new SimpleShell(new File("."), stdin, stdout);
	}

	@Override
	public void run() {
		shell.runInteractiveShell();
		shutdown();
	}

	private void shutdown() {
		try {
			stdin.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Could not close stdin", e);
		}
		try {
			stdout.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Could not close stdout", e);
		}
	}
}
