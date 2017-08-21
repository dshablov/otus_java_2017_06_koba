package ru.otus.cache;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

/**
 * User: Vladimir Koba
 * Date: 20.08.2017
 * Time: 20:40
 */
public class SimpleCacheEngine<K, V> implements CacheEngine<K, V> {
    private static final int TIME_THRESHOLD_MS = 1;

    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;

    private final Map<K, SoftReference<CacheElement>> elements = new LinkedHashMap<>();
    private final Timer timer = new Timer();

    private int hit = 0;
    private int miss = 0;

    public SimpleCacheEngine(int maxElements, long lifeTimeMs, long idleTimeMs) {
        this.maxElements = maxElements;
        this.lifeTimeMs = lifeTimeMs > 0 ? lifeTimeMs : 0;
        this.idleTimeMs = idleTimeMs > 0 ? idleTimeMs : 0;
    }


    @Override
    public void put(CacheElement<K, V> element) {
        deleteFirstElementIfCacheFull();
        K key = putElementIntoCache(element);
        checkCacheForTTL(key);
        checkCacheForIdleTime(key);
    }

    @Override
    public CacheElement<K, V> get(K key) {
        SoftReference<CacheElement> elementSoftReference = elements.get(key);
        CacheElement<K, V> cacheElement = null;
        if (elementSoftReference != null) {
            cacheElement = elementSoftReference.get();
            if (cacheElement != null) {
                cacheElement.refreshLastAccess();
                hit++;
            }
        } else {
            miss++;
        }
        return cacheElement;
    }

    @Override
    public int hitCount() {
        return hit;
    }


    @Override
    public int missCount() {
        return miss;
    }


    private void checkCacheForIdleTime(K key) {
        if (idleTimeMs != 0) {
            TimerTask idleTimerTask = getTimerTask(key, idleElement -> idleElement.creationTime() + idleTimeMs);
            timer.schedule(idleTimerTask, idleTimeMs);
        }
    }

    private void checkCacheForTTL(K key) {
        if (lifeTimeMs != 0) {
            TimerTask lifeTimerTask = getTimerTask(key, lifeElement -> lifeElement.creationTime() + lifeTimeMs);
            timer.schedule(lifeTimerTask, lifeTimeMs);
        }
    }

    private K putElementIntoCache(CacheElement<K, V> element) {
        K key = element.key();
        elements.put(key, new SoftReference<>(element));
        return key;
    }

    private void deleteFirstElementIfCacheFull() {
        if (elements.size() == maxElements) {
            K firstKey = elements.keySet().iterator().next();
            elements.remove(firstKey);
        }
    }

    @Override
    public void dispose() {
        timer.cancel();
    }

    private TimerTask getTimerTask(final K key, Function<CacheElement<K, V>, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                SoftReference<CacheElement> checkedReference = elements.get(key);
                if (checkedReference == null ||
                        isT1BeforeT2(timeFunction.apply(checkedReference.get()), System.currentTimeMillis())) {
                    elements.remove(key);
                }
            }
        };
    }


    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }
}
