package com.janosgyerik.telnetserver.run;

import com.janosgyerik.telnetserver.server.TelnetServer;

import java.io.IOException;

public class TelnetServerRunner {

	private static final int PORT = 1234;

	public static void main(String[] args) throws IOException {
		new TelnetServer().runForever(PORT);
	}
}
