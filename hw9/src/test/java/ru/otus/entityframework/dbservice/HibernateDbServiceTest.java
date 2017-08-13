package ru.otus.entityframework.dbservice;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.otus.domain.UserDataSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * User: Vladimir Koba
 * Date: 13.08.2017
 * Time: 23:32
 */
public class HibernateDbServiceTest {

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
        DbService rdbExecutor = new HibernateDbService();
        rdbExecutor.save(new UserDataSet(null, "Vasya", 5));
        UserDataSet loadedUser = rdbExecutor.load(1L, UserDataSet.class);
        assertNotNull(loadedUser);
        loadedUser.setName("Petya");
        rdbExecutor.save(loadedUser);
        UserDataSet updatedUser = rdbExecutor.load(1L, UserDataSet.class);
        assertEquals("Petya", updatedUser.getName());
    }
}