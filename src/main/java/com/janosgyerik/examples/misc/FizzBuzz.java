package com.janosgyerik.examples.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FizzBuzz {
    private final List<Fizzer> fizzers;

    private static class Fizzer {
        private final String name;
        private final int divisor;

        private Fizzer(String name, int divisor) {
            this.name = name;
            this.divisor = divisor;
        }
    }

    public static class Builder {
        private final List<Fizzer> fizzers = new ArrayList<>();

        public Builder add(String name, int divisor) {
            fizzers.add(new Fizzer(name, divisor));
            return this;
        }

        public FizzBuzz build() {
            return new FizzBuzz(this);
        }
    }

    private FizzBuzz(Builder builder) {
        fizzers = Collections.unmodifiableList(builder.fizzers);
    }

    public String getValue(int num) {
        String output = "";
        for (Fizzer fizzer : fizzers) {
            if (num % fizzer.divisor == 0) {
                output += fizzer.name;
            }
        }
        return output.isEmpty() ? Integer.toString(num) : output;
    }

    public static Builder builder() {
        return new Builder();
    }
}
