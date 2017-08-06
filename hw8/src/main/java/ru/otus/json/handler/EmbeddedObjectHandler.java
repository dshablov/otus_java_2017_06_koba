package ru.otus.json.handler;

import org.json.simple.JSONObject;
import ru.otus.json.TypeHandler;

import java.lang.reflect.Field;

/**
 * User: Vladimir Koba
 * Date: 04.08.2017
 * Time: 0:50
 */
public class EmbeddedObjectHandler extends TypeHandler {
    @Override
    public boolean handle(JSONObject result, Field field, Object object) {
        if (field.get(object) != null) {
            result.put(field.getName(), toJson(field.get(object)));
        }
    }
}
