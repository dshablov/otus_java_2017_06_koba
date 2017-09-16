package ru.otus;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * User: Vladimir Koba
 * Date: 16.09.2017
 * Time: 20:49
 */
public class QuarterThreadSorterTest {


    @Test
    public void validSort() {
        List<Long> unsortedArray = generateUnsortedArray(100_000);
        QuarterThreadSorter quarterThreadSorter = new QuarterThreadSorter(unsortedArray);
        List<Long> sortedArray = quarterThreadSorter.oldSchoolSort();
        assertThat(sortedArray).isSorted();
    }


    private static List<Long> generateUnsortedArray(int size) {
        List<Long> array = new ArrayList<>();
        for (long i = 0; i < size; i++) {
            array.add(i);
        }
        Collections.shuffle(array);
        return array;
    }

}