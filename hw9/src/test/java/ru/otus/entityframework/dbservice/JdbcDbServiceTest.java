package ru.otus.entityframework.dbservice;

import ru.otus.domain.UserDataSet;
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

    public static final String connectionString = "jdbc:sqlite:sample.db";

    @BeforeClass
    public static void initDriver() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        try (Connection connection = DriverManager.getConnection(connectionString);
             Statement statement = connection.createStatement();) {
            statement.execute("DELETE  FROM USER");
        }
    }


    @Test
    public void complexTest() {
        JdbcDbService rdbExecutor = new JdbcDbService(connectionString);
        rdbExecutor.save(new UserDataSet(5l, "Vasya", 5));
        UserDataSet loadedUser = rdbExecutor.load(5L, UserDataSet.class);
        assertNotNull(loadedUser);
        loadedUser.setName("Petya");
        rdbExecutor.save(loadedUser);
        UserDataSet updatedUser = rdbExecutor.load(5L, UserDataSet.class);
        assertEquals("Petya", updatedUser.getName());
    }


}