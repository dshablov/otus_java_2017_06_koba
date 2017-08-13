package ru.otus.entityframework.handler;

import ru.otus.domain.UserDataSet;
import ru.otus.entityframework.DataSet;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Vladimir Koba
 * Date: 12.08.2017
 * Time: 0:48
 */
public class SqlResultHandlerFactory {
    private static Map<Class<?>, SqlResultHandler> map = new HashMap<>();

    static {
        map.put(UserDataSet.class, new UserDataSetSqlResultHandler());
    }

    public static <T extends DataSet> SqlResultHandler<T> findHandlerByClass(Class<T> clazz) {
        if (clazz == null) {
            throw new RuntimeException("Clazz cannot be null");
        }
        if (map.get(clazz) == null) {
            throw new RuntimeException("Type handler not found for " + clazz.getName());
        }
        return map.get(clazz);
    }
}
