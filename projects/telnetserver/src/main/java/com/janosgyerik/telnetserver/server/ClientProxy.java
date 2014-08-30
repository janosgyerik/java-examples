package com.janosgyerik.telnetserver.server;

import com.janosgyerik.telnetserver.shell.Shell;
import com.janosgyerik.telnetserver.shell.SimpleShell;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientProxy implements Runnable {

	private final Shell shell;

	public ClientProxy(Socket socket) throws IOException {
		InputStream stdin = socket.getInputStream();
		OutputStream stdout = socket.getOutputStream();
		shell = new SimpleShell(new File("."), stdin, stdout);
	}

	@Override
	public void run() {
		shell.runInteractiveShell();
	}
}
