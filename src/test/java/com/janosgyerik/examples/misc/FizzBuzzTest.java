package com.janosgyerik.examples.misc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {
    @Test
    public void test_Without_Fizzer() {
        FizzBuzz fizzBuzz = FizzBuzz.builder().build();
        assertEquals("1", fizzBuzz.getValue(1));
        assertEquals("2", fizzBuzz.getValue(2));
        assertEquals("3", fizzBuzz.getValue(3));
        assertEquals("4", fizzBuzz.getValue(4));
        assertEquals("5", fizzBuzz.getValue(5));
        assertEquals("15", fizzBuzz.getValue(15));
        assertEquals("16", fizzBuzz.getValue(16));
        assertEquals(Integer.toString(31 * 3 * 5), fizzBuzz.getValue(31 * 3 * 5));
        assertEquals(Integer.toString(31 * 3 * 5 + 1), fizzBuzz.getValue(31 * 3 * 5 + 1));
    }

    @Test
    public void test_With_Fizz() {
        FizzBuzz fizzBuzz = FizzBuzz.builder().add("Fizz", 3).build();
        assertEquals("1", fizzBuzz.getValue(1));
        assertEquals("2", fizzBuzz.getValue(2));
        assertEquals("Fizz", fizzBuzz.getValue(3));
        assertEquals("4", fizzBuzz.getValue(4));
        assertEquals("5", fizzBuzz.getValue(5));
        assertEquals("Fizz", fizzBuzz.getValue(15));
        assertEquals("16", fizzBuzz.getValue(16));
        assertEquals("Fizz", fizzBuzz.getValue(31 * 3 * 5));
        assertEquals(Integer.toString(31 * 3 * 5 + 1), fizzBuzz.getValue(31 * 3 * 5 + 1));
    }

    @Test
    public void test_With_FizzBuzz() {
        FizzBuzz fizzBuzz = FizzBuzz.builder().add("Fizz", 3).add("Buzz", 5).build();
        assertEquals("1", fizzBuzz.getValue(1));
        assertEquals("2", fizzBuzz.getValue(2));
        assertEquals("Fizz", fizzBuzz.getValue(3));
        assertEquals("4", fizzBuzz.getValue(4));
        assertEquals("Buzz", fizzBuzz.getValue(5));
        assertEquals("FizzBuzz", fizzBuzz.getValue(15));
        assertEquals("16", fizzBuzz.getValue(16));
        assertEquals("FizzBuzz", fizzBuzz.getValue(31 * 3 * 5));
        assertEquals(Integer.toString(31 * 3 * 5 + 1), fizzBuzz.getValue(31 * 3 * 5 + 1));
    }

    @Test
    public void test_With_FizzBuzzJazz() {
        FizzBuzz fizzBuzz = FizzBuzz.builder().add("Fizz", 3).add("Buzz", 5).add("Jazz", 7).build();
        assertEquals("1", fizzBuzz.getValue(1));
        assertEquals("2", fizzBuzz.getValue(2));
        assertEquals("Fizz", fizzBuzz.getValue(3));
        assertEquals("4", fizzBuzz.getValue(4));
        assertEquals("Buzz", fizzBuzz.getValue(5));
        assertEquals("FizzBuzz", fizzBuzz.getValue(15));
        assertEquals("16", fizzBuzz.getValue(16));
        assertEquals("FizzBuzz", fizzBuzz.getValue(31 * 3 * 5));
        assertEquals(Integer.toString(31 * 3 * 5 + 1), fizzBuzz.getValue(31 * 3 * 5 + 1));
        assertEquals("FizzBuzzJazz", fizzBuzz.getValue(31 * 3 * 5 * 7));
    }

    @Test
    public void test_BuilderAdd_DifferentDivisorOrder_SameOutput() {
        FizzBuzz fizzBuzz = FizzBuzz.builder().add("Fizz", 3).add("Buzz", 5).build();
        FizzBuzz buzzFizz = FizzBuzz.builder().add("Buzz", 5).add("Fizz", 3).build();

        assertEquals(fizzBuzz.getValue(15), buzzFizz.getValue(15));
    }
}
