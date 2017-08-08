package ru.otus.json.handler;

import org.json.simple.JSONObject;

/**
 * User: Vladimir Koba
 * Date: 08.08.2017
 * Time: 23:27
 */
public class EmptyHandler extends TypeHandler {
    @Override
    public void handle(JSONObject result, String fieldName, Class<?> fieldType, Object fieldValue) {
        return;
    }

    @Override
    protected void applyHandler(JSONObject result, String fieldName, Object fieldValue) {

    }

    @Override
    protected boolean isAppliableHandler(Class<?> fieldType, Object fieldValue) {
        return false;
    }
}
