package entityframework.query;

import entityframework.DataSet;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Vladimir Koba
 * Date: 09.08.2017
 * Time: 23:58
 */
public class UpdateByIdQuery extends AbstractSqlQuery implements SqlQuery {

    private final String tableName;
    private final DataSet entity;
    private final Map<String, String> nameToValue;


    public UpdateByIdQuery(String tableName, DataSet entity) {
        this.tableName = tableName;
        this.entity = entity;
        nameToValue = new LinkedHashMap<>();
    }


    @Override
    public void addParameter(Class<?> fieldType, String name, String value) {
        addWithApostrophsIfNeeded(fieldType, name, value);
    }

    private void addWithApostrophsIfNeeded(Class<?> fieldType, String name, String value) {
        if (fieldType.equals(String.class)) {
            nameToValue.put(name, "'" + value + "'");
        } else {
            nameToValue.put(name, value);
        }
    }

    //                                         Дописать апдейт, сделать тест, поправить метод сейв
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
