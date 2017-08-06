package ru.otus.json.handler;

import org.json.simple.JSONObject;
import ru.otus.json.TypeHandler;


import java.lang.reflect.Field;

/**
 * User: Vladimir Koba
 * Date: 01.08.2017
 * Time: 21:58
 */
public class PrimitiveHandler extends TypeHandler {

    @Override
    public boolean handle(JSONObject result, Field field, Object object) {
        try {
            if (field.getType().isPrimitive()) {
                result.put(field.getName(), field.get(object));
                return true;
            }
            return false;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
