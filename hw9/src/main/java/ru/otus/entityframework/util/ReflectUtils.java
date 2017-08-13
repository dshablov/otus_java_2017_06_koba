package ru.otus.entityframework.util;

import javax.persistence.Table;

/**
 * User: Vladimir Koba
 * Date: 13.08.2017
 * Time: 22:50
 */
public class ReflectUtils {

    public static String tableNameFromMetadata(Class<?> clazz) {
        Table table = clazz.getAnnotation(Table.class);
        if (table == null) {
            throw new UnsupportedOperationException("There is no @Table annotation");
        }
        return table.name();
    }
}
