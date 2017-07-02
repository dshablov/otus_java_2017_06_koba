package ru.otus.test;

import ru.otus.kunit.annotation.After;
import ru.otus.kunit.annotation.Before;
import ru.otus.kunit.annotation.Test;

import static ru.otus.kunit.core.KAsserts.*;

/**
 * User: Vladimir Koba
 * Date: 01.07.2017
 * Time: 21:55
 */
public class SomeTest {
    private int a = 0;
    private int b = 0;

    @Before
    public void beforeTest() {
        a = 2;
        b = 2;
    }

    @Test
    public void sum_2_and_2_is_4_withAssertTrue() {
        assertTrue(a + b == 4);
    }

    @Test
    public void error_test_sum_2_and_2_is_5_withAssertTrue() {
        assertTrue(a + b == 5);
    }

    @Test
    public void test_sum_2_and_2_is_4_withAssertEquals() {
        assertEquals(2, 2);
    }

    @Test
    public void error_test_sum_2_and_2_is_5_withAssertEquals() {
        assertEquals(a + 1, b);
    }

    @Test
    public void test_not_null() {
        assertNotNull(1);
    }

    @Test
    public void error_test_not_null() {
        assertNotNull(null);
    }


    @After
    public void afterTest() {
        a = 2;
        b = 3;
    }


    public void notTesteddMethod() {

    }
}
