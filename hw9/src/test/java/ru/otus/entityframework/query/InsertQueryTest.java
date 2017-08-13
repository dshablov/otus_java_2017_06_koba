package ru.otus.entityframework.query;

import ru.otus.domain.UserDataSet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: Vladimir Koba
 * Date: 10.08.2017
 * Time: 0:07
 */
public class InsertQueryTest {

    @Test
    public void generateValidInsert() {
        InsertQuery query = new InsertQuery("testTable", new UserDataSet(5L, "qqq", 3));
        assertEquals("insert into testTable (id,name,age) values (5,'qqq',3)", query.bulid());
    }


}