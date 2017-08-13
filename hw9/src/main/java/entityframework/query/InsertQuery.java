package entityframework.query;

import entityframework.DataSet;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: Vladimir Koba
 * Date: 09.08.2017
 * Time: 23:58
 */
public class InsertQuery extends AbstractSqlQuery implements SqlQuery  {

    private final String tableName;
    private final Map<String, String> nameToValue;
    private final DataSet entity;


    public  InsertQuery(String tableName, DataSet entity) {
        this.tableName = tableName;
        this.entity = entity;
        nameToValue = new LinkedHashMap<>();
    }


    @Override
    public void addParameter(Class<?> fieldType, String name, String value) {
        addWithApostrophsIfNeeded(nameToValue, fieldType, name, value);
    }



    @Override
    public String bulid() {
        addParametersToQueryFromObject(entity,this);
        return "insert into %tableName% (%names%) values (%values%)"
                .replaceAll("%tableName%", tableName)
                .replaceAll("%names%", String.join(",", nameToValue.keySet()))
                .replaceAll("%values%", String.join(",", nameToValue.values()));
    }
}
