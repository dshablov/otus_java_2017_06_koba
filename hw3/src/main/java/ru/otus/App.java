package ru.otus;


import java.util.*;
import java.util.stream.IntStream;

/**
 * Печатаем циферки из csv-файлов, который находится в ресурсах
 */
public class App {
    public static void main(String[] args) {
        //Проверяем Collections.addAll(..)
        List<Integer> megaInts = new MegaArrayList<>();
        System.out.print("Create megaInts collection from 1 to 10 elements inclusive..");
        Collections.addAll(megaInts, IntStream.range(1, 11).boxed().toArray(Integer[]::new));
        printList(megaInts);

        //Проверяем Collections.copy(..)
        List<Integer> forCopied = new MegaArrayList<>();
        System.out.print("Create forCopied collections from 91 to 100 elements inclusive..");
        Collections.addAll(forCopied, IntStream.range(91, 101).boxed().toArray(Integer[]::new));
        printList(forCopied);
        Collections.copy(megaInts, forCopied);
        System.out.print("Copied from forCopied collection to ints:");
        printList(megaInts);

        //Проверяем Collections.sort(..)
        System.out.print("Sort ints in reversal order");
        Collections.sort(megaInts, Comparator.comparing(Integer::intValue).reversed());
        printList(megaInts);


    }

    private static void printList(List<Integer> ints) {
        System.out.println(Arrays.toString(ints.toArray()));
    }
}
