package ru.otus.entityframework.dbservice;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.otus.domain.AddressDataSet;
import ru.otus.domain.PhoneDataSet;
import ru.otus.domain.UserDataSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.*;

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
    public void cacheTest() {
        CacheInfo cacheInfo = new CacheInfo(0);
        DbService rdbExecutor = new CachedHibernateDbService(cacheInfo);
        UserDataSet vasya = new UserDataSet(null, "Vasya", 5, new AddressDataSet("address"), asList(new PhoneDataSet("123-12-12")));
        rdbExecutor.save(vasya);
        for (int i = 0; i < 100; i++) {
            rdbExecutor.load(vasya.getId());
        }
        assertTrue(cacheInfo.cacheHits() == 100);

    }


    @Test
    public void complexTest() {
        DbService rdbExecutor = new HibernateDbService();
        UserDataSet vasya = new UserDataSet(null, "Vasya", 5, new AddressDataSet("address"), asList(new PhoneDataSet("123-12-12")));
        rdbExecutor.save(vasya);
        UserDataSet loadedUser = rdbExecutor.load(1L);
        assertNotNull(loadedUser);
        assertNotNull(loadedUser.getAddress());
        assertNotNull(loadedUser.getPhones());
        assertTrue(loadedUser.getPhones().size() > 0);
        loadedUser.setName("Petya");
        rdbExecutor.save(loadedUser);
        UserDataSet updatedUser = rdbExecutor.load(1L);
        assertEquals("Petya", updatedUser.getName());
    }
}