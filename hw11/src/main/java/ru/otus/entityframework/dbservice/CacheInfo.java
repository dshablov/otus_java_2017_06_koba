package ru.otus.entityframework.dbservice;

/**
 * User: Vladimir Koba
 * Date: 20.08.2017
 * Time: 22:20
 */
public class CacheInfo {
    private int cacheHits;

    public CacheInfo(int cacheHits) {
        this.cacheHits = cacheHits;
    }

    public int cacheHits() {
        return cacheHits;
    }

    public void incHits() {
        cacheHits++;
    }
}
