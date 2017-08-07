package ru.otus.json.handler;

import org.apache.commons.lang3.ClassUtils;
import org.json.simple.JSONObject;

/**
 * User: Vladimir Koba
 * Date: 01.08.2017
 * Time: 21:58
 */
public class PrimitiveHandler extends TypeHandler {

    @Override
    public void handle(JSONObject result, String fieldName, Class<?> fieldType, Object fieldValue) {
        try {
            if (ClassUtils.isPrimitiveOrWrapper(fieldType)) {
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
