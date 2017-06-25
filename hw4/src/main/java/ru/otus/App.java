package ru.otus;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: Vladimir Koba
 * Date: 26.06.2017
 * Time: 1:06
 */

/**
 * Стенд для исследования работы GC. Создает ступенчатую линейную нагрузку.
 * (создает ELEMENTS_FOR_ITERATION объектов, удаляет ELEMENTS_FOR_ITERATION / 2 каждые PAUSE_BETWEEN_ITERATIONS)
 *
 * Каждую секунду регистрируется количество вызов GC разных поколений, в конце выводится статистика поминутно.
 *
 *
 */
public class App {
    public static final int ELEMENTS_FOR_ITERATION = 50000;
    public static final int PAUSE_BETWEEN_ITERATIONS = 100;
    private static List<String> youngGc = Arrays.asList(new String[]{"Copy", "ParNew", "PS Scavenge", "G1 Young Generation"});
    private static List<String> oldGc = Arrays.asList(new String[]{"MarkSweepCompact", "PS MarkSweep", "ConcurrentMarkSweep", "G1 Old Generation"});
    private static long youngGenerationCalls = 0L;
    private static long youngGenerationTimeToCollect = 0L;
    private static long oldGenerationCalls = 0L;
    private static long oldGenerationTimeToCollect = 0L;
    private static List<StatRecord> statisticRecords = new ArrayList<>();


    public static void main(String[] args) throws InterruptedException {
        subscribeGc();
        long startTime = System.currentTimeMillis();

        long everySecond;
        try {
            System.out.println("Waiting OOM..");
            List<String> elements = new ArrayList<>();
            everySecond = System.currentTimeMillis();

            while (true) {
                if (System.currentTimeMillis() - everySecond > 1000L) {
                    everySecond = System.currentTimeMillis();
                    statisticRecords.add(new StatRecord(LocalDateTime.now(), youngGenerationCalls, oldGenerationCalls, youngGenerationTimeToCollect, oldGenerationTimeToCollect));
                    youngGenerationCalls = 0L;
                    youngGenerationTimeToCollect = 0L;
                    oldGenerationCalls = 0L;
                    oldGenerationTimeToCollect = 0L;
                }

                for (int i = 0; i < ELEMENTS_FOR_ITERATION; ++i) {
                    elements.add("e" + i);
                }

                elements.removeIf((element) -> Integer.valueOf(element.substring(1)) % 2 == 0);
                Thread.sleep(PAUSE_BETWEEN_ITERATIONS);
            }
        } catch (OutOfMemoryError e) {
            everySecond = System.currentTimeMillis();
            System.out.println("Working time before OOM: " + (everySecond - startTime) / 1000L + "s");
            new Statistic(statisticRecords).print();
        }
    }

    private static void subscribeGc() {
        List<GarbageCollectorMXBean> gcbeans = ManagementFactory.getGarbageCollectorMXBeans();
        NotificationListener listener = (notification, handback) -> {
            GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
            if (youngGc.contains(info.getGcName())) {
                ++youngGenerationCalls;
                youngGenerationTimeToCollect += info.getGcInfo().getDuration();
            } else if (oldGc.contains(info.getGcName())) {
                ++oldGenerationCalls;
                oldGenerationTimeToCollect += info.getGcInfo().getDuration();
            }

        };
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            System.out.println("Garbage collector name:" + gcbean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            emitter.addNotificationListener(listener, null, null);
        }

    }
}
