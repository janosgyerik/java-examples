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

Running
-------

Run the telnet server on port 1234 with:

    java -jar target/telnetserver.jar 1234

Stop the server with Control-C.

Adding commands
---------------

To add new commands, extend the `BaseCommand` abstract class,
and it will be discovered automatically if the class is
on the runtime classpath.
