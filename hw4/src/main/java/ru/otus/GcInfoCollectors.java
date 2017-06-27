package ru.otus;

import javax.management.NotificationEmitter;
import java.util.List;

/**
 * User: Vladimir Koba
 * Date: 27.06.2017
 * Time: 23:03
 */
public class GcInfoCollectors {
    private final List<GcInfoCollector> collectors;

    public GcInfoCollectors(List<GcInfoCollector> collectors) {
        this.collectors = collectors;
    }

    public void subscribeTo(NotificationEmitter emitter) {
        for (GcInfoCollector gcc : collectors) {
            emitter.addNotificationListener(gcc, null, null);
        }
    }

    public void writeMetricsShapshot() {
        for (GcInfoCollector gcc : collectors) {
            gcc.writeMetricsSnapshot();
            gcc.resetMetrics();
        }
    }

    public void printStatistic() {
        Statistic statistic = new Statistic();
        for (GcInfoCollector collector : collectors) {
            statistic.add(collector.statistic());
        }
        statistic.print();
    }
}
