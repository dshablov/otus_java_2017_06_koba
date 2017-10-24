package ru.otus.cache;

/**
 * User: Vladimir Koba
 * Date: 20.08.2017
 * Time: 20:33
 */
public interface CacheEngine<K, V> {


    void put(CacheElement<K, V> element);

    CacheElement<K, V> get(K key);

    int hitCount();

    int missCount();

    void dispose();
}
