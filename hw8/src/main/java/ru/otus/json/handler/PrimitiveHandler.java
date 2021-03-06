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
    protected void applyHandler(JSONObject result, String fieldName, Object fieldValue) {
        result.put(fieldName, fieldValue);

    }

    @Override
    protected boolean isAppliableHandler(Class<?> fieldType, Object fieldValue) {
        return ClassUtils.isPrimitiveOrWrapper(fieldType);
    }
}
