//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.otus;

import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Statistic {
    private final List<StatRecord> records;


    public Statistic() {
        records = new ArrayList<>();
    }

    public Statistic(List<StatRecord> records) {
        this.records = records;
    }

    public void add(Statistic statistic) {
        this.records.addAll(statistic.records);
    }

    public void print() {
        Map<Integer, List<StatRecord>> minuteToRecords = new HashMap<>();
        fillMinuteToRecordsMap(minuteToRecords);
        minuteToRecords.forEach((minute, records) -> {
            System.out.println("In " + minute + " minute:");
            System.out.println("Young generation calls: " + records.stream().mapToLong(StatRecord::youngGenerationCalls).sum());
            System.out.println("Old generation calls: " + records.stream().mapToLong(StatRecord::oldGenerationCalls).sum());
            System.out.println("Young generation total time to collect: " + records.stream().mapToLong(StatRecord::youngGenerationTimeToCollect).sum() + "ms");
            System.out.println("Old generation total time to collect: " + records.stream().mapToLong(StatRecord::oldGenerationTimeToCollect).sum() + "ms");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        });
        System.out.println("Total:");
        System.out.println("Young generation calls: " + this.records.stream().mapToLong(StatRecord::youngGenerationCalls).sum());
        System.out.println("Old generation calls: " + this.records.stream().mapToLong(StatRecord::oldGenerationCalls).sum());
        System.out.println("Young generation total time to collect: " + this.records.stream().mapToLong(StatRecord::youngGenerationTimeToCollect).sum() + "ms");
        System.out.println("Old generation total time to collect: " + this.records.stream().mapToLong(StatRecord::oldGenerationTimeToCollect).sum() + "ms");
    }

    private void fillMinuteToRecordsMap(Map<Integer, List<StatRecord>> minuteToRecords) {
        int startMinuteStamp = this.records.get(0).timestamp().get(ChronoField.MINUTE_OF_DAY);

        for (StatRecord stat : records) {
            int minute = stat.timestamp().get(ChronoField.MINUTE_OF_DAY);
            if (minuteToRecords.get(minute - startMinuteStamp + 1) == null) {
                List<StatRecord> statRecords = new ArrayList<>();
                statRecords.add(stat);
                minuteToRecords.put(minute - startMinuteStamp + 1, statRecords);
            } else {
                List<StatRecord> statRecords = minuteToRecords.get(minute - startMinuteStamp + 1);
                statRecords.add(stat);
            }
        }

    }
}
