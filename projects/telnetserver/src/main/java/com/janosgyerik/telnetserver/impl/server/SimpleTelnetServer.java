package com.janosgyerik.telnetserver.impl.server;

import com.janosgyerik.telnetserver.server.ClientProxy;
import com.janosgyerik.telnetserver.server.TelnetServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class SimpleTelnetServer implements TelnetServer {

	private static final Logger LOGGER = Logger.getLogger(SimpleTelnetServer.class.getSimpleName());

	private static final int SO_TIMEOUT = 1000;

	private volatile boolean stopRequested;

	private void runForever(int port) throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(SO_TIMEOUT);

		LOGGER.info("Listening on port: " + port);

		List<ClientProxy> clients = new ArrayList<>();

		while (!stopRequested) {
			try {
				Socket socket = serverSocket.accept();

				LOGGER.info(String.format("New customer: %s:%s",
						socket.getInetAddress(), socket.getPort()));

				SimpleClientProxy client = new SimpleClientProxy(socket);
				clients.add(client);

				new Thread(client).start();

			} catch (SocketTimeoutException e) {
				// accept timed out, continue listening unless stop requested
			}
			removeStaleClients(clients);
		}

		LOGGER.info("Stop requested, shutting down ...");

		for (ClientProxy client : clients) {
			client.shutdown();
		}
	}

	private void removeStaleClients(List<ClientProxy> clients) {
		Iterator<ClientProxy> iterator = clients.iterator();
		while (iterator.hasNext()) {
			if (!iterator.next().isAlive()) {
				LOGGER.info("Removing stale client ...");
				iterator.remove();
			}
		}
	}

	@Override
	public void start(int port) throws IOException {
		runForever(port);
	}

	@Override
	public void shutdown() {
		stopRequested = true;
	}
}

