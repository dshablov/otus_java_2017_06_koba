package entityframework.query;

import domain.UserDataSet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: Vladimir Koba
 * Date: 12.08.2017
 * Time: 11:22
 */
public class UpdateByIdQueryTest {
    @Test
    public void generateValidUpdate() {
        UpdateByIdQuery query = new UpdateByIdQuery("testTable", new UserDataSet(1L, "qqq", 3));
        assertEquals("update testTable set id=1,name='qqq',age=3 where id=1", query.bulid());
    }

}