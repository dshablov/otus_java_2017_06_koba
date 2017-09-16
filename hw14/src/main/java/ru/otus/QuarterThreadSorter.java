package ru.otus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * User: Vladimir Koba
 * Date: 16.09.2017
 * Time: 20:44
 */
public class QuarterThreadSorter {
    private final List<Long> array;

    public QuarterThreadSorter(List<Long> array) {
        this.array = array;
    }

    public List<Long> oldSchoolSort() {
        try {
            List<List<Long>> subarrays = splitToSubArrays(4);
            Thread manageThread = new Thread(() -> {
                for (List<Long> subarray : subarrays) {
                    Thread thread = new Thread(() -> subarray.sort(Comparator.naturalOrder()));
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        //ignore it
                    }
                }
            });
            manageThread.start();
            manageThread.join();

            return merge(subarrays);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<List<Long>> splitToSubArrays(int count) {
        List<List<Long>> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(new ArrayList<>());
        }
        int targetArray = 0;
        for (Long el : array) {
            result.get(targetArray).add(el);
            targetArray++;
            if (targetArray == count) {
                targetArray = 0;
            }
        }
        return result;
    }

    /**
     *  Внимание! Merge k сортированных массивов в один честно сперт со StackOverflow.
     *  Алгоритм не особо эффективен из-за операции remove(), но просто для понимания и прозрачен.
     */
    public  List<Long> merge(List<List<Long>> lists) {
        int totalSize = 0;
        for (List<Long> l : lists) {
            totalSize += l.size();
        }
        List<Long> result = new ArrayList<>(totalSize);
        List<Long> lowest;
        while (result.size() < totalSize) { 
            lowest = null;

            for (List<Long> l : lists) {
                if (!l.isEmpty()) {
                    if (lowest == null) {
                        lowest = l;
                    } else if (l.get(0).compareTo(lowest.get(0)) <= 0) {
                        lowest = l;
                    }
                }
            }
            result.add(lowest.get(0));
            lowest.remove(0);
        }
        return result;
    }

}
