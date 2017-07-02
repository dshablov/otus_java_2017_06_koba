package ru.otus.kunit.core;

import ru.otus.kunit.exception.KAssertException;

/**
 * User: Vladimir Koba
 * Date: 02.07.2017
 * Time: 18:06
 */
public class KAsserts {

    public static void assertTrue(boolean expression) {
        if (!expression) {
            throw new KAssertException("Expected true, but false");
        }
    }

    public static void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new KAssertException("Objects aren't equals. Expected: " + expected + " but found " + actual);
        }
    }

    public static void assertNotNull(Object actual) {
        if (actual == null) {
            throw new KAssertException("Actual object is null");
        }
    }
}
