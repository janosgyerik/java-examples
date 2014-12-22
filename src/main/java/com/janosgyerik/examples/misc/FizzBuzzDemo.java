package com.janosgyerik.examples.misc;

import static java.util.stream.IntStream.rangeClosed;

class FizzBuzzDemo {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = FizzBuzz.builder().add("Fizz", 3).add("Buzz", 5).build();
        rangeClosed(1, 100).mapToObj(fizzBuzz::getValue).forEach(System.out::println);
    }
}
