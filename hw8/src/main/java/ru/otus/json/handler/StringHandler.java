package ru.otus.json.handler;

import org.json.simple.JSONObject;

/**
 * User: Vladimir Koba
 * Date: 04.08.2017
 * Time: 0:25
 */
public class StringHandler extends TypeHandler {

    @Override
    public void handle(JSONObject result, String fieldName, Class<?> fieldType, Object fieldValue) {
        try {
            if (fieldType.getSimpleName().equals("String")) {
                result.put(fieldName, fieldValue);
                return;
            }
            if (hasNext()) {
                handleNext(result, fieldName, fieldType, fieldValue);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
