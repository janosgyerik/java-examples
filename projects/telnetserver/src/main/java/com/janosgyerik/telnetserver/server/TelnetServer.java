package com.janosgyerik.telnetserver.server;

import java.io.IOException;

public interface TelnetServer {

    /**
     * Start listening on a port and accept incoming connections
     *
     * @param port to listen on
     */
    void start(int port) throws IOException;

    /**
     * Stop accepting connections and shutdown all clients
     */
    void shutdown();
}
