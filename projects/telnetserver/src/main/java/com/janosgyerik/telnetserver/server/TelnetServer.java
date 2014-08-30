package com.janosgyerik.telnetserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class TelnetServer {
	private static final Logger LOGGER = Logger.getLogger(TelnetServer.class.getSimpleName());

	private static final int PORT = 1234;

	public static void main(String[] args) throws IOException {
		new TelnetServer().runForever(PORT);
	}

	public void runForever(int port) throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		LOGGER.info("Listening on port: " + port);

		while (true) {
			Socket socket = serverSocket.accept();
			LOGGER.info("New customer: " + socket.getInetAddress());
			ClientProxy client = new ClientProxy(socket);
			new Thread(client).start();
		}
	}
}

