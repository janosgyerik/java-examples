package com.janosgyerik.telnetserver.server;

public interface ClientProxy {

    /**
     * Shutdown, close streams
     */
    void shutdown();

    /**
     * Check if client is alive or not
     *
     * @return is alive or not
     */
    boolean isAlive();
}
