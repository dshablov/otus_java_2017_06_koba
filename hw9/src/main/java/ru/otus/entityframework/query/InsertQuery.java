package ru.otus.entityframework.query;

import ru.otus.entityframework.DataSet;

/**
 * User: Vladimir Koba
 * Date: 09.08.2017
 * Time: 23:58
 */
public class InsertQuery extends ChangeTableQuery {


    public InsertQuery(String tableName, DataSet entity) {
        super(tableName, entity);
    }


    @Override
    public String bulid() {
        addParametersToQueryFromObject(entity, this);
        return "insert into %tableName% (%names%) values (%values%)"
                .replaceAll("%tableName%", tableName)
                .replaceAll("%names%", String.join(",", nameToValue.keySet()))
                .replaceAll("%values%", String.join(",", nameToValue.values()));
    }
}
