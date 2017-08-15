package ru.otus.entityframework.query;

import ru.otus.entityframework.DataSet;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: Vladimir Koba
 * Date: 15.08.2017
 * Time: 23:51
 */
public abstract class ChangeTableQuery extends AbstractSqlQuery {
    protected final String tableName;
    protected final Map<String, String> nameToValue;
    protected final DataSet entity;


    public ChangeTableQuery(String tableName,  DataSet entity) {
        this.tableName = tableName;
        this.nameToValue = new LinkedHashMap<>();
        this.entity = entity;
    }

    @Override
    public void addParameter(Class<?> fieldType, String name, String value) {
        addWithApostrophsIfNeeded(nameToValue, fieldType, name, value);
    }
}
