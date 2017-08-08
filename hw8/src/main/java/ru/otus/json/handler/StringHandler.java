package ru.otus.json.handler;

import org.json.simple.JSONObject;

/**
 * User: Vladimir Koba
 * Date: 04.08.2017
 * Time: 0:25
 */
public class StringHandler extends TypeHandler {


    @Override
    protected void applyHandler(JSONObject result, String fieldName, Object fieldValue) {
        result.put(fieldName, fieldValue);
    }

    @Override
    protected boolean isAppliableHandler(Class<?> fieldType, Object fieldValue) {
        return fieldType.getSimpleName().equals("String");
    }
}
