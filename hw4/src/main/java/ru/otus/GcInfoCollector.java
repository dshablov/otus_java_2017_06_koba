package ru.otus;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Vladimir Koba
 * Date: 27.06.2017
 * Time: 22:25
 */
public class GcInfoCollector implements NotificationListener {
    private final List<String> gcNames;
    private final GcType gcType;
    private long gcCalls;
    private long gcTimeToCollect;
    private List<StatRecord> statistic;


    public GcInfoCollector(List<String> gcNames, GcType gcType) {
        this.gcNames = gcNames;
        this.gcType = gcType;
        statistic = new ArrayList<>();
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {
        GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
        if (gcNames.contains(info.getGcName())) {
            gcCalls++;
            gcTimeToCollect += info.getGcInfo().getDuration();
        }
    }

    public void writeMetricsSnapshot() {
        switch (gcType) {
            case OLD:
                statistic.add(new StatRecord(LocalDateTime.now(), 0, gcCalls, 0, gcTimeToCollect));
                break;
            case YOUNG:
                statistic.add(new StatRecord(LocalDateTime.now(), gcCalls, 0, gcCalls, 0));
                break;
            default:
                throw new RuntimeException("Unknown GC type " + gcType);
        }
    }

    public void resetMetrics() {
        gcCalls = 0;
        gcTimeToCollect = 0;
    }

    public Statistic statistic() {
        return new Statistic(statistic);
    }

}




