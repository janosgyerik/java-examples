package com.janosgyerik.telnetserver.run;

import com.janosgyerik.telnetserver.server.TelnetServer;

import java.io.IOException;

public class TelnetServerRunner {

	private static final int DEFAULT_PORT = 1234;

	public static void main(String[] args) throws IOException {
		int port = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;
		new TelnetServer().runForever(port);
	}
}
