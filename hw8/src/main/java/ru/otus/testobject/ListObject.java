package ru.otus.testobject;


import java.util.List;

import static java.util.Arrays.asList;

/**
 * User: Vladimir Koba
 * Date: 01.08.2017
 * Time: 23:24
 */
public class ListObject {
    private List<String> stringList;
//    private List<Boolean> booleanList = asList(true, false);
//    private List<Integer> intList = asList(1, 2, 3);

    public ListObject() {
        stringList = asList("a123","b123","c123");
    }
}
