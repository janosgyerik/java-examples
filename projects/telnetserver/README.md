Simple telnet server
====================

A simple, extensible, multi-threaded telnet server.

Supported commands:

- cd
- pwd
- ls
- mkdir

Building
--------

To create an executable jar, run:

    mvn package

The jar file will be created in `target/telnetserver.jar`.

Running the server
------------------

Run the telnet server on port 1234 with:

    java -jar target/telnetserver.jar 1234

Stop the server with Control-C.

Running clients
---------------

Connect to the server using the `telnet` command, for example:

    telnet localhost 1234

To exit, press Control-D and Enter.