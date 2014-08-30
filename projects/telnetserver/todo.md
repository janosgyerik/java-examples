Bare minimum
------------

- bug: ls incorrect
    ls src  # ok
    cd src
    ls      # ok
    ls main # NOT ok, empty output
    ls test # NOT ok, empty output

- enter should print prompt again

- make commands write to stdout directly instead of returning array
    - and make them return boolean exit code for success / failure

- add stderr

- make Control-C not break the client

- exit client with Control-D if possible

Nice to have
------------

- make the shell chrooted