package ru.otus.entityframework.handler;

import ru.otus.domain.UserDataSet;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: Vladimir Koba
 * Date: 12.08.2017
 * Time: 0:41
 */
public class UserDataSetSqlResultHandler implements SqlResultHandler<UserDataSet> {

    @Override
    public UserDataSet handle(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            return new UserDataSet(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"));
        }
        return null;
    }
}
