package com.janosgyerik.telnetserver.impl.server;

import com.janosgyerik.telnetserver.impl.shell.SimpleShell;
import com.janosgyerik.telnetserver.server.ClientProxy;
import com.janosgyerik.telnetserver.shell.Shell;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleClientProxy implements Runnable, ClientProxy {

    private static final Logger LOGGER = Logger.getLogger(SimpleClientProxy.class.getSimpleName());

    private final Shell shell;
    private final InputStream stdin;
    private final OutputStream stdout;

    private volatile boolean shuttingDown;

    public SimpleClientProxy(Socket socket) throws IOException {
        stdin = socket.getInputStream();
        stdout = socket.getOutputStream();
        shell = new SimpleShell(new File("."), stdin, stdout);
    }

    @Override
    public void run() {
        shell.runInteractiveShell();
        shutdown();
    }

    @Override
    public void shutdown() {
        if (shuttingDown) {
            return;
        }
        shuttingDown = true;

        LOGGER.info("Shutting down client ...");

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

    @Override
    public boolean isAlive() {
        return !shuttingDown;
    }
}
