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
    private final CacheEngine<String, UserDataSet> cacheEngine;
    private CacheInfo cacheInfo;


    public CachedHibernateDbService(CacheInfo cacheInfo) {
        this();
        this.cacheInfo = cacheInfo;
    }

    public CachedHibernateDbService() {
        this.dbService = new HibernateDbService();
        this.cacheEngine = new SimpleCacheEngine<>(10, 1200000, 100000);
        this.cacheInfo = null;
    }

    @Override
    public void save(UserDataSet user) {
        dbService.save(user);
        cacheEngine.put(new CacheElement<>(user.getLogin() + user.getPassword(), user));
    }

    @Override
    public UserDataSet load(Long id) {
        return dbService.load(id);
    }

    @Override
    public UserDataSet loadByUsernameAndPassword(String username, String password) {
        CacheElement<String, UserDataSet> cachedUser = cacheEngine.get(username + password);
        if (cachedUser != null) {
            if (cacheInfo != null) {
                cacheInfo.hits(cacheEngine.hitCount());
            }
            return cachedUser.value();
        }
        UserDataSet loadedUser = dbService.loadByUsernameAndPassword(username, password);
        if (loadedUser != null) {
            cacheEngine.put(new CacheElement<>(loadedUser.getLogin() + loadedUser.getPassword(), loadedUser));
        }
        return loadedUser;
    }
}
