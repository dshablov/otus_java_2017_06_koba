package ru.otus;

import javax.management.NotificationEmitter;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * User: Vladimir Koba
 * Date: 26.06.2017
 * Time: 1:06
 */

/**
 * Стенд для исследования работы GC. Создает ступенчатую линейную нагрузку.
 * (создает ELEMENTS_FOR_ITERATION объектов, удаляет ELEMENTS_FOR_ITERATION / 2 каждые PAUSE_BETWEEN_ITERATIONS)
 * <p>
 * Каждую секунду регистрируется количество вызов GC разных поколений, в конце выводится статистика поминутно.
 */
public class App {
    public static final int ELEMENTS_FOR_ITERATION = 150000;
    public static final int PAUSE_BETWEEN_ITERATIONS = 100;


    public static void main(String[] args) throws InterruptedException {
        GcInfoCollectors collectors = new GcInfoCollectors(asList(
                new GcInfoCollector(asList("Copy", "ParNew", "PS Scavenge", "G1 Young Generation"), GcType.YOUNG),
                new GcInfoCollector(asList("MarkSweepCompact", "PS MarkSweep", "ConcurrentMarkSweep", "G1 Old Generation"), GcType.OLD)
        ));
        subscribeCollectorsToGcCollectEvent(collectors);
        long startTime = System.currentTimeMillis();
        long everySecond;
        try {
            System.out.println("Waiting OOM..");
            List<String> elements = new ArrayList<>();
            everySecond = System.currentTimeMillis();
            while (true) {
                if (System.currentTimeMillis() - everySecond > 1000L) {
                    everySecond = System.currentTimeMillis();
                    collectors.writeMetricsShapshot();
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
            collectors.printStatistic();
        }
    }

    private static void subscribeCollectorsToGcCollectEvent(GcInfoCollectors collectors) {
        List<GarbageCollectorMXBean> gcbeans = ManagementFactory.getGarbageCollectorMXBeans();
        System.out.println("Used Gc:");
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            System.out.println(" - " + gcbean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            collectors.subscribeTo(emitter);
        }

    }
}
