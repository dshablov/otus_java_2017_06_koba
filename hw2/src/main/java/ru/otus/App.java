package ru.otus;

import java.text.MessageFormat;


public class App {


    public static void main(String... args) throws InterruptedException {
        System.out.println(MessageFormat.format("Empty string size is {0} bytes (with string pool)",
                MemoryTestStand.plug(() -> new String("")).test()));
        System.out.println(MessageFormat.format("Empty string size is {0} (without string pool)",
                MemoryTestStand.plug(() -> new String(new char[0])).test()));
        System.out.println(MessageFormat.format("1 element int array size is {0}",
                MemoryTestStand.plug(() -> new int[0]).test()));
        System.out.println(MessageFormat.format("100 element int array size is {0}",
                MemoryTestStand.plug(() -> new int[99]).test()));
        System.out.println(MessageFormat.format("1000 element int array size is {0}",
                MemoryTestStand.plug(() -> new int[999]).test()));
        System.out.println(MessageFormat.format("10000 element int array size is {0}",
                MemoryTestStand.plug(() -> new int[9999]).test()));
        System.out.println(MessageFormat.format("Class size with int and long variable is {0} bytes",
                MemoryTestStand.plug(Primitives::new).test()));
        System.out.println(MessageFormat.format("1 element Primitives array size is {0} bytes",
                MemoryTestStand.plug(() -> new Primitives[0]).test()));
        System.out.println(MessageFormat.format("100 element Primitives array size is {0} bytes",
                MemoryTestStand.plug(() -> new Primitives[99]).test()));
        System.out.println(MessageFormat.format("1000 element Primitives array size is {0} bytes",
                MemoryTestStand.plug(() -> new Primitives[999]).test()));
        System.out.println(MessageFormat.format("10000 element Primitives array size is {0} bytes",
                MemoryTestStand.plug(() -> new Primitives[9999]).test()));
    }


}

