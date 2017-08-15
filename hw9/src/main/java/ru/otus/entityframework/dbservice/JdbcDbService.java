package ru.otus.entityframework.dbservice;

import ru.otus.entityframework.DataSet;
import ru.otus.entityframework.query.InsertQuery;
import ru.otus.entityframework.query.SelectIdSqlQuery;
import ru.otus.entityframework.query.SqlQuery;
import ru.otus.entityframework.query.UpdateByIdQuery;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static ru.otus.entityframework.util.ReflectUtils.tableNameFromMetadata;

/**
 * User: Vladimir Koba
 * Date: 09.08.2017
 * Time: 23:55
 */
public class JdbcDbService implements DbService {


    private final String connectionString;


    public JdbcDbService(String connectionString) {
        this.connectionString = connectionString;
    }

    @Override
    public <T extends DataSet> void save(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        try {
            DataSet loadedEntity = load(entity.getId(), entity.getClass());
            SqlQuery query;
            if (loadedEntity == null) {
                query = new InsertQuery(tableNameFromMetadata(entity.getClass()), entity);
            } else {
                query = new UpdateByIdQuery(tableNameFromMetadata(entity.getClass()), entity);
            }
            executeQuery(query.bulid());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) {
        try {
            SqlQuery selectQuery = new SelectIdSqlQuery(tableNameFromMetadata(clazz), id);
            return executeSelect(selectQuery.bulid(), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void executeQuery(String sql) throws ClassNotFoundException, SQLException {
        try (Connection connection = DriverManager.getConnection(connectionString);
             Statement statement = connection.createStatement();) {
            statement.execute(sql);
        }
    }

    private <T> T executeSelect(String sql, Class<T> clazz) throws ClassNotFoundException, SQLException {
        try (Connection connection = DriverManager.getConnection(connectionString);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            return handle(resultSet, clazz);
        }
    }

    private <T> T handle(ResultSet resultSet, Class<T> clazz) {
        try {
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            T obj = clazz.newInstance();
            List<Field> allFields = collectFieldsFromClassAndSuperclass(clazz, obj);
            for (Field field : allFields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    if (field.getName().equalsIgnoreCase("id")) {
                        field.set(obj, resultSet.getLong(column.name()));
                    } else {
                        field.set(obj, resultSet.getObject(column.name()));
                    }
                }
            }
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> List<Field> collectFieldsFromClassAndSuperclass(Class<T> clazz, T obj) {
        List<Field> allFields = new ArrayList<>();
        allFields.addAll(asList(clazz.getDeclaredFields()));
        if (obj.getClass().getSuperclass().isAnnotationPresent(MappedSuperclass.class)) {
            allFields.addAll(asList(obj.getClass().getSuperclass().getDeclaredFields()));
        }
        return allFields;
    }
}
