package ru.otus.entityframework.dbservice;

import ru.otus.entityframework.DataSet;

/**
 * User: Vladimir Koba
 * Date: 09.08.2017
 * Time: 23:55
 */
public interface DbService {
    
    <T extends DataSet> void save(T user);

    <T extends DataSet> T load(long id, Class<T> clazz);


}
