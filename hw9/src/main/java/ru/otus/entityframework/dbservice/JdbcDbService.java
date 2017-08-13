package ru.otus.entityframework.dbservice;

import ru.otus.entityframework.DataSet;
import ru.otus.entityframework.handler.SqlResultHandler;
import ru.otus.entityframework.handler.SqlResultHandlerFactory;
import ru.otus.entityframework.query.InsertQuery;
import ru.otus.entityframework.query.SelectIdSqlQuery;
import ru.otus.entityframework.query.SqlQuery;
import ru.otus.entityframework.query.UpdateByIdQuery;

import java.sql.*;

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
            return executeSelect(selectQuery.bulid(), SqlResultHandlerFactory.findHandlerByClass(clazz));
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

    private <T> T executeSelect(String sql, SqlResultHandler<T> handler) throws ClassNotFoundException, SQLException {
        try (Connection connection = DriverManager.getConnection(connectionString);
             Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(sql);
            return handler.handle(resultSet);
        }
    }


}
