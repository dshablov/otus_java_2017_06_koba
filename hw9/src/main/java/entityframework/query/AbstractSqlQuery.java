package entityframework.query;

import entityframework.DataSet;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;

/**
 * User: Vladimir Koba
 * Date: 13.08.2017
 * Time: 22:30
 */
public abstract class AbstractSqlQuery {

    protected <T extends DataSet> void addParametersToQueryFromObject(T user, SqlQuery query) {
        addFieldsFromSuperclass(user, query);
        addFieldsToInsertQuery(user.getClass().getDeclaredFields(), user, query);
    }

    private <T extends DataSet> void addFieldsFromSuperclass(T user, SqlQuery insertQuery) {
        if (user.getClass().getSuperclass().isAnnotationPresent(MappedSuperclass.class)) {
            addFieldsToInsertQuery(user.getClass().getSuperclass().getDeclaredFields(), user, insertQuery);
        }
    }

    private <T extends DataSet> void addFieldsToInsertQuery(Field[] fields, T user, SqlQuery insertQuery) {
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
