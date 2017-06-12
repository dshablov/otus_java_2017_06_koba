package ru.otus;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: Vladimir Koba
 * Date: 12.06.2017
 * Time: 22:30
 */
public class MemoryTestStandTest {

    @Test
    public void emptyStringSizeWithStringPoolIs24bytes() {
        assertEquals(24L, MemoryTestStand.plug(() -> new String("")).test());
    }

    @Test
    public void emptyStringSizeWithoutStringPoolIs40bytes() {
        assertEquals(40L, MemoryTestStand.plug(() -> new String(new char[0])).test());
    }

    @Test
    public void classWithIntAndLongFieldsSizeIs24bytes() {
        assertEquals(24L, MemoryTestStand.plug(() -> new Primitives()).test());
    }
}