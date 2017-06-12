package ru.otus;

import java.text.MessageFormat;

/**
 * VM options -Xmx512m -Xms512m
 * <p>
 * Runtime runtime = Runtime.getRuntime();
 * long mem = runtime.totalMemory() - runtime.freeMemory();
 * <p>
 * System.gc()
 * <p>
 * jconsole, connect to pid
 */


public class App {


    public static void main(String... args) throws InterruptedException {
        System.out.println(MessageFormat.format("Empty string size is {0} bytes (with string pool)",
                MemoryTestStand.plug(() -> new String("")).test()));
        System.out.println(MessageFormat.format("Empty string size is {0} (without string pool)",
                MemoryTestStand.plug(() -> new String(new char[0])).test()));
        System.out.println(MessageFormat.format("Class size with int and long variable is {0} bytes",
                MemoryTestStand.plug(Primitives::new).test()));
    }
}

