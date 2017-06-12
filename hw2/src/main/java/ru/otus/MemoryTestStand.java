package ru.otus;

import java.util.function.Supplier;


/**
 * Тестовый стенд, предназначен для измерения размера переданного объекта
 */
public class MemoryTestStand {
    private final Supplier testObject;
    private final int TEST_COUNT = 7_000_000;


    /**
     * Именованный конструктор для создания тестового стенда. Хотелось реализовать метафору тестового стенда,
     * в который "втыкают" объект, а потом проводят измерения.
     *
     * @param testObject объект, размер которого надо измерить
     */
    public static MemoryTestStand plug(Supplier testObject) {
        return new MemoryTestStand(testObject);
    }

    private MemoryTestStand(Supplier testObject) {
        this.testObject = testObject;
    }

    /**
     * @return размер переданного объекта в байтах
     */
    public long test() {
        Object[] testObjects = new Object[TEST_COUNT];
        runGC();
        long heapBeforeObjectCreation = usedHeap();
        fillingTestObjects(testObjects);
        long heapAfterObjectCreation = usedHeap();
        cleanupTestObjects(testObjects);
        return Math.round(((float) (heapAfterObjectCreation - heapBeforeObjectCreation)) / TEST_COUNT);
    }

    private void fillingTestObjects(Object[] testObjects) {
        for (int i = 0; i < TEST_COUNT; i++) {
            testObjects[i] = testObject.get();
        }
    }

    private long usedHeap() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    private void cleanupTestObjects(Object[] objects) {
        for (int i = 0; i < TEST_COUNT; ++i) {
            objects[i] = null;
        }
        objects = null;
        System.gc();
    }

    private void runGC() {
        try {
            System.gc();
            Thread.sleep(700);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error while runGC. Details:", e);
        }
    }


}
