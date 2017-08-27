package ru.otus.entityframework.dbservice;

/**
 * User: Vladimir Koba
 * Date: 20.08.2017
 * Time: 22:20
 */
public class CacheInfo {
    private long cacheHits;

    public CacheInfo(long cacheHits) {
        this.cacheHits = cacheHits;
    }

    public long cacheHits() {
        return cacheHits;
    }

    public void hits(long hits) {
        this.cacheHits = hits;
    }
}
