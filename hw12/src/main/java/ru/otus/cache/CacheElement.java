package ru.otus.cache;

/**
 * User: Vladimir Koba
 * Date: 20.08.2017
 * Time: 20:34
 */
public class CacheElement<K, V> {
    private final K key;
    private final V value;
    private final long creationTime;
    private long lastAccessTime;

    public CacheElement(K key, V value) {
        this.key = key;
        this.value = value;
        this.creationTime = currentTime();
        this.lastAccessTime = currentTime();

    }

    public K key() {
        return key;
    }

    public V value() {
        return value;
    }

    public long creationTime() {
        return creationTime;
    }

    public long lastAccessTime() {
        return lastAccessTime;
    }

    protected long currentTime() {
        return System.currentTimeMillis();
    }

    public void refreshLastAccess() {
        lastAccessTime = currentTime();
    }
}
