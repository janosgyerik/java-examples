package com.janosgyerik.examples.multicolumn;

public class ParseError {

    private final String message;

    public ParseError(int index, String value) {
        message = String.format("Could not parse value '%s' in column %d", value, index);
    }

    @Override
    public String toString() {
        return message;
    }
}
