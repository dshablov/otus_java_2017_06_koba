package ru.otus;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Печатаем циферки из csv-файлов, который находится в ресурсах
 */
public class App {
    public static void main(String[] args) {
        //Проверяем Collections.addAll()

        List<Integer> ints = new MegaArrayList<>();
        ints.subList(0,0);
        Collections.addAll(ints, IntStream.range(1, 11).boxed().toArray(Integer[]::new));
        System.out.println(ints.get(0));
    }
}
