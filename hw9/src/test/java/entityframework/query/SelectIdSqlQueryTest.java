package entityframework.query;

import entityframework.query.SelectIdSqlQuery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: Vladimir Koba
 * Date: 11.08.2017
 * Time: 23:56
 */


public class SelectIdSqlQueryTest {

    @Test
    public void generateValidSelectId() {
        SelectIdSqlQuery query = new SelectIdSqlQuery("testTable", 1L);
        assertEquals("select * from testTable where id = 1", query.bulid());
    }
}