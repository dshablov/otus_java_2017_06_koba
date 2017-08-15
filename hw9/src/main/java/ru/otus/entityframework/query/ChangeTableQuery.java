package ru.otus.entityframework.query;

import ru.otus.entityframework.DataSet;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: Vladimir Koba
 * Date: 13.08.2017
 * Time: 22:30
 */
public abstract class ChangeTableQuery implements SqlQuery {

    protected final String tableName;
    protected final Map<String, String> nameToValue;
    protected final DataSet entity;

    public ChangeTableQuery(String tableName, DataSet entity) {
        this.tableName = tableName;
        this.nameToValue = new LinkedHashMap<>();
        this.entity = entity;
    }

    @Override
    public void addParameter(Class<?> fieldType, String name, String value) {
        addWithApostrophsIfNeeded(nameToValue, fieldType, name, value);
    }

    protected <T extends DataSet> void addParametersToQueryFromObject(T user, SqlQuery query) {
        addFieldsFromSuperclass(user, query);
        addFieldsToQuery(user.getClass().getDeclaredFields(), user, query);
    }

    protected void addWithApostrophsIfNeeded(Map<String, String> nameToValue, Class<?> fieldType, String name, String value) {
        if (fieldType.equals(String.class)) {
            nameToValue.put(name, "'" + value + "'");
        } else {
            nameToValue.put(name, value);
        }
    }

    private <T extends DataSet> void addFieldsFromSuperclass(T user, SqlQuery insertQuery) {
        if (user.getClass().getSuperclass().isAnnotationPresent(MappedSuperclass.class)) {
            addFieldsToQuery(user.getClass().getSuperclass().getDeclaredFields(), user, insertQuery);
        }
    }

    private <T extends DataSet> void addFieldsToQuery(Field[] fields, T user, SqlQuery insertQuery) {
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                insertQuery.addParameter(field.getType(), columnNameFromMetadata(field), String.valueOf(field.get(user)));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    private String columnNameFromMetadata(Field field) {
        Column column = field.getAnnotation(Column.class);
        if (column == null) {
            throw new UnsupportedOperationException("There is no @Column annotation on field");
        }
        return column.name();
    }
}
