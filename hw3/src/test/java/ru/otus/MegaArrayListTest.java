package ru.otus;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Ricoshet on 17.06.2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MegaArrayListTest {


    @Test
    public void addSomeNewElementsIntoEmptyMegaArrayList() {
        List<Integer> ints = new MegaArrayList<>();
        Collections.addAll(ints, 1, 2, 3);
        assertThat(ints.size()).isEqualTo(3);
        assertThat(ints).containsExactly(1, 2, 3);
    }

    @Test
    public void addManyNewElementsIntoEmptyMegaArrayList() {
        List<Integer> ints = new MegaArrayList<>();
        Collections.addAll(ints, IntStream.range(0, 400).boxed().toArray(Integer[]::new));
        assertThat(ints.size()).isEqualTo(400);
        assertThat(ints).contains(1, 2, 199);
    }

    @Test
    public void addAllAddedAllElementsToMegaArrayList() {
        List<Integer> ints = new MegaArrayList<>();
        ints.addAll(asList(1, 2, 3));
        assertThat(ints.size()).isEqualTo(3);
        assertThat(ints).containsExactly(1, 2, 3);
    }

    @Test
    public void addAllWithIndexInMiddleAddedElementsAndShiftElementsToRight() {
        List<Integer> ints = new MegaArrayList<>();
        ints.addAll(asList(1, 2, 3));
        ints.addAll(1, asList(3, 3, 3));
        assertThat(ints.size()).isEqualTo(6);
        assertThat(ints).containsExactly(1, 3, 3, 3, 2, 3);
    }

    @Test
    public void addAllWithIndexInFirstAddedElementsAndShiftElementsToRight() {
        List<Integer> ints = new MegaArrayList<>();
        ints.addAll(asList(1, 2, 3));
        ints.addAll(0, asList(3, 3, 3));
        assertThat(ints.size()).isEqualTo(6);
        assertThat(ints).containsExactly(3, 3, 3, 1, 2, 3);
    }

    @Test
    public void addAllWithBigIndexAddedElementSequentiallyToTheEndOfArray() {
        List<Integer> ints = new MegaArrayList<>();
        ints.addAll(asList(1, 2, 3));
        ints.addAll(50, asList(3, 3, 3));
        assertThat(ints.size()).isEqualTo(6);
        assertThat(ints).containsExactly(1, 2, 3, 3, 3, 3);
    }

    @Test
    public void addElementInMiddleShiftCollectionToRight() {
        List<Integer> ints = new MegaArrayList<>();
        ints.addAll(asList(1, 2, 3));
        ints.add(1, 4);
        assertThat(ints.size()).isEqualTo(4);
        assertThat(ints).containsExactly(1, 4, 2, 3);
    }

    @Test
    public void checkContainsForOneElement() {
        List<Integer> ints = new MegaArrayList<>();
        Collections.addAll(ints, 1, 2, 3);
        assertThat(ints.contains(2)).isTrue();
        assertThat(ints.contains(4)).isFalse();
    }

    @Test
    public void checkContainsForManyElement() {
        List<Integer> ints = new MegaArrayList<>();
        Collections.addAll(ints, 1, 2, 3);
        assertThat(ints.containsAll(asList(1, 2))).isTrue();
        assertThat(ints.containsAll(asList(3, 4))).isFalse();
    }

    @Test
    public void copyMegaArrayListToArrayList() {
        List<Integer> sourceInts = new MegaArrayList<>();
        List<Integer> destInts = new ArrayList<>();
        Collections.addAll(sourceInts, 1, 2, 3);
        Collections.addAll(destInts, 4, 5, 6);
        Collections.copy(destInts, sourceInts);
        assertThat(sourceInts).containsExactlyElementsOf(destInts);
    }

    @Test
    public void copyArrayListToMegaArrayList() {
        List<Integer> sourceInts = new ArrayList<>();
        List<Integer> destInts = new MegaArrayList<>();
        Collections.addAll(sourceInts, 1, 2, 3);
        Collections.addAll(destInts, 4, 5, 6);
        Collections.copy(destInts, sourceInts);
        assertThat(sourceInts).containsExactlyElementsOf(destInts);
    }

    @Test
    public void checkIndexAndLastIndexOfElement() {
        List<Integer> ints = new ArrayList<>();
        ints.addAll(asList(1, 2, 3, 2, 5));
        assertThat(ints.indexOf(2)).isEqualTo(1);
        assertThat(ints.lastIndexOf(2)).isEqualTo(3);
    }

    @Test
    public void removeLastElementFromMegaArrayList() {
        List<Integer> ints = new MegaArrayList<>();
        Collections.addAll(ints, 2, 1, 5, 2, 0);
        ints.remove(4);
        assertThat(ints.size()).isEqualTo(4);
        assertThat(ints).contains(2, 1, 5, 2);
        assertThat(ints).doesNotContain(0);
    }

    @Test
    public void removeFirstElementFromMegaArrayList() {
        List<Integer> ints = new MegaArrayList<>();
        Collections.addAll(ints, 7, 1, 5, 2, 0);
        ints.remove(0);
        assertThat(ints.size()).isEqualTo(4);
        assertThat(ints).contains(1, 5, 2, 0);
        assertThat(ints).doesNotContain(7);
    }

    @Test
    public void removeMiddleElementFromMegaArrayList() {
        List<Integer> ints = new MegaArrayList<>();
        Collections.addAll(ints, 7, 1, 5, 2, 0);
        ints.remove(2);
        assertThat(ints.size()).isEqualTo(4);
        assertThat(ints).contains(7, 1, 2, 0);
        assertThat(ints).doesNotContain(5);
    }

    @Test
    public void removeAllElementsFromMegaArrayList() {
        List<Integer> ints = new MegaArrayList<>();
        Collections.addAll(ints, 7, 1, 5, 2, 0);
        ints.removeAll(asList(1, 0));
        assertThat(ints.size()).isEqualTo(3);
        assertThat(ints).contains(7, 5, 2);
    }

    @Test
    public void retainAllElementsFromMegaArrayList() {
        List<Integer> ints = new MegaArrayList<>();
        Collections.addAll(ints, 7, 1, 5, 2, 0);
        ints.retainAll(asList(1, 0));
        assertThat(ints.size()).isEqualTo(2);
        assertThat(ints).contains(1, 0);
    }


    @Test
    public void sortMegaArrayInReverseOrder() {
        List<Integer> ints = new MegaArrayList<>();
        Collections.addAll(ints, 2, 1, 5, 2, 0);
        Collections.sort(ints, reversedIntComparator());
        assertThat(ints).isSortedAccordingTo(reversedIntComparator());
    }

    @Test
    public void sublistReturnCorrectElements() {
        List<Integer> ints = new MegaArrayList<>();
        Collections.addAll(ints, 1, 2, 3, 4, 5);
        assertThat(ints.subList(1, 3)).hasSize(2).contains(2, 3);
    }

    @Test
    public void zeroElementMegaListArrayIsEmpty() {
        List<Integer> ints = new MegaArrayList<>();
        assertThat(ints.isEmpty()).isTrue();
        Collections.addAll(ints, 2, 1, 5, 2, 0);
        assertThat(ints.isEmpty()).isFalse();
    }


    private Comparator<Integer> reversedIntComparator() {
        return Comparator.comparing(Integer::intValue);
    }


}