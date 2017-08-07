package ru.otus.json;

import com.google.gson.Gson;
import org.junit.Test;
import ru.otus.testobject.ComplexObject;
import ru.otus.testobject.SimpleObject;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * User: Vladimir Koba
 * Date: 08.08.2017
 * Time: 0:10
 */
public class KJsonParserTest {

    @Test
    public void convertSimpleObjectWithoutArraysAndLists() {
        SimpleObject simpleObj = new SimpleObject("asdf", 123, new SimpleObject("gfds", 321, new SimpleObject("432", 4, null)));

        KJsonParser parser = new KJsonParser();
        Gson gson = new Gson();
        SimpleObject reooveredObject = gson.fromJson(parser.toJson(simpleObj).toJSONString(), SimpleObject.class);
        assertEquals(simpleObj, reooveredObject);
    }

    @Test
    public void convertComplexObject() {
        SimpleObject simpleObj = new SimpleObject("asdf", 123, new SimpleObject("gfds", 321, null));
        ComplexObject complexObject = new ComplexObject("stringField", new String[]{"1", "23"},
                4, new int[]{1, 2, 3}, new ArrayList<>(asList("1", "2")), simpleObj, new ArrayList<>(asList(simpleObj)));

        KJsonParser parser = new KJsonParser();
        Gson gson = new Gson();
        ComplexObject reooveredObject = gson.fromJson(parser.toJson(complexObject).toJSONString(), ComplexObject.class);
        assertEquals(complexObject, reooveredObject);
    }

}