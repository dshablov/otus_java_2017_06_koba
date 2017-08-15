package ru.otus.entityframework.query;

import ru.otus.entityframework.DataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Vladimir Koba
 * Date: 09.08.2017
 * Time: 23:58
 */
public class UpdateByIdQuery extends ChangeTableQuery {


    public UpdateByIdQuery(String tableName, DataSet entity) {
        super(tableName,entity);
    }

    
    @Override
    public String bulid() {
        addParametersToQueryFromObject(entity, this);
        return "update %tableName% set %namesvalues% where id=%id%"
                .replaceAll("%tableName%", tableName)
                .replaceAll("%namesvalues%", String.join(",", mapToKeyValueList()))
                .replaceAll("%id%", String.valueOf(entity.getId()));
    }

    private List<String> mapToKeyValueList() {
        List<String> records = new ArrayList<>();
        nameToValue.forEach((name, value) -> {
            records.add(name + "=" + value);
        });
        return records;
    }
}
