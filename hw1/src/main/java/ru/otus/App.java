package ru.otus;

import au.com.bytecode.opencsv.CSVReader;

import java.io.InputStreamReader;

/**
 * Печатаем циферки из csv-файлов, который находится в ресурсах
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Starting csv reader..");
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(App.class.getClassLoader().getResourceAsStream("numbers.csv"), "UTF-8"));
            reader.readAll().forEach(number ->
                    System.out.println(number[0]));
        } catch (Exception e) {
            System.out.println("Error while reading numbers.. Details:");
            e.printStackTrace();
        }
    }
}
