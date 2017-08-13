package entityframework.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: Vladimir Koba
 * Date: 12.08.2017
 * Time: 0:41
 */
public interface SqlResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;

}
