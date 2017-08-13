package entityframework.dbservice;

import domain.UserDataSet;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * User: Vladimir Koba
 * Date: 10.08.2017
 * Time: 0:31
 */
public class JdbcDbServiceTest {

    @BeforeClass
    public static void initDriver() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
             Statement statement = connection.createStatement();) {
            statement.execute("DELETE  FROM USER");
        }
    }


    @Test
    public void complexTest() {
        JdbcDbService rdbExecutor = new JdbcDbService();
        rdbExecutor.save(new UserDataSet(5L, "Vasya", 5));
        UserDataSet loadedUser = rdbExecutor.load(5L, UserDataSet.class);
        assertNotNull(loadedUser);
        loadedUser.setName("Petya");
        rdbExecutor.save(loadedUser);
        UserDataSet updatedUser = rdbExecutor.load(5L, UserDataSet.class);
        assertEquals("Petya", updatedUser.getName());
    }


}