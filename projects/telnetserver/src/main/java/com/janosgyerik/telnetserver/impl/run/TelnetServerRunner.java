package com.janosgyerik.telnetserver.impl.run;

import com.janosgyerik.telnetserver.impl.server.SimpleTelnetServer;
import com.janosgyerik.telnetserver.server.TelnetServer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TelnetServerRunner {

	private static final int DEFAULT_PORT = 1234;

	public static void main(String[] args) throws IOException, InterruptedException {
		final int port = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;
		final TelnetServer server = new SimpleTelnetServer();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					server.start(port);
				} catch (IOException e) {
					// e.printStackTrace();
				}
			}
		}).start();
		TimeUnit.SECONDS.sleep(30);
		server.shutdown();
	}
}
