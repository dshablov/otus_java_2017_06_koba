package ru.otus.entityframework.dbservice;

import ru.otus.cache.CacheElement;
import ru.otus.cache.CacheEngine;
import ru.otus.cache.SimpleCacheEngine;
import ru.otus.domain.UserDataSet;

/**
 * User: Vladimir Koba
 * Date: 20.08.2017
 * Time: 22:00
 */
public class CachedHibernateDbService implements DbService {
    private final HibernateDbService dbService;
    private final CacheEngine<Long, UserDataSet> cacheEngine;
    private CacheInfo cacheInfo;


    public CachedHibernateDbService(CacheInfo cacheInfo) {
        this();
        this.cacheInfo = cacheInfo;
    }

    public CachedHibernateDbService() {
        this.dbService = new HibernateDbService();
        this.cacheEngine = new SimpleCacheEngine<>(5, 120000, 10000);
        this.cacheInfo = null;
    }

    @Override
    public void save(UserDataSet user) {
        dbService.save(user);
        cacheEngine.put(new CacheElement<>(user.getId(), user));
    }

    @Override
    public UserDataSet load(Long id) {
        CacheElement<Long, UserDataSet> cachedUser = cacheEngine.get(id);
        if (cachedUser != null) {
            if (cacheInfo != null) {
                cacheInfo.hits(cacheEngine.hitCount());
            }
            return cachedUser.value();
        }
        UserDataSet loadedUser = dbService.load(id);
        if (loadedUser != null) {
            cacheEngine.put(new CacheElement<>(loadedUser.getId(), loadedUser));
        }
        return loadedUser;
    }
}
