package com.janosgyerik.examples.misc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

class Triangle {

    private final int a;
    private final int b;
    private final int c;

    public static enum Type {
        EQUILATERAL {
            @Override
            boolean matches(Triangle t) {
                return t.a == t.b && t.b == t.c;
            }
        },
        ISOSCELES {
            @Override
            boolean matches(Triangle t) {
                return t.a == t.b || t.a == t.c || t.b == t.c;
            }
        },
        SCALENE {
            @Override
            boolean matches(Triangle t) {
                return !ISOSCELES.matches(t);
            }
        };

        abstract boolean matches(Triangle t);

        public static Type classify(Triangle t) {
            for (Type type : values()) {
                if (type.matches(t)) {
                    return type;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        if (!isSaneArguments(a, b, c)) {
            throw new IllegalArgumentException("All sides of a Triangle must be > 0");
        }
        if (!isTriangle(a, b, c)) {
            throw new IllegalArgumentException("Not a triangle: no side must be longer than the sum of the other sides");
        }
    }

    private static boolean isSaneArguments(int a, int b, int c) {
        return a > 0 && b > 0 && c > 0;
    }

    private static boolean isTriangle(int a, int b, int c) {
        return a < b + c && b < a + c && c < a + b;
    }
}

public class TriangleTest {
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTriangleWithTooLongSide() {
        int a = 1;
        int b = a + 1;
        new Triangle(a, b, a + b + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTriangleWithNegativeSide() {
        new Triangle(1, 2, 0);
    }

    @Test
    public void testScalene() {
        assertEquals(Triangle.Type.SCALENE, Triangle.Type.classify(new Triangle(2, 3, 4)));
    }

    @Test
    public void testEquilateral() {
        assertEquals(Triangle.Type.EQUILATERAL, Triangle.Type.classify(new Triangle(3, 3, 3)));
        assertEquals(Triangle.Type.EQUILATERAL, Triangle.Type.classify(new Triangle(2, 2, 2)));
        assertNotEquals(Triangle.Type.EQUILATERAL, Triangle.Type.classify(new Triangle(3, 2, 2)));
        assertNotEquals(Triangle.Type.EQUILATERAL, Triangle.Type.classify(new Triangle(3, 2, 4)));
    }

    @Test
    public void testIsosceles() {
        assertEquals(Triangle.Type.ISOSCELES, Triangle.Type.classify(new Triangle(2, 3, 3)));
        assertEquals(Triangle.Type.ISOSCELES, Triangle.Type.classify(new Triangle(3, 3, 2)));
        assertEquals(Triangle.Type.ISOSCELES, Triangle.Type.classify(new Triangle(3, 2, 3)));
    }
}
