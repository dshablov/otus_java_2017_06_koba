package ru.otus.json.handler;

import org.json.simple.JSONObject;
import ru.otus.json.KJsonParser;

/**
 * User: Vladimir Koba
 * Date: 04.08.2017
 * Time: 0:50
 */
public class EmbeddedObjectHandler extends TypeHandler {
    private final KJsonParser parser;

    public EmbeddedObjectHandler(KJsonParser parser) {
        this.parser = parser;
    }


    @Override
    protected void applyHandler(JSONObject result, String fieldName, Object fieldValue) {
        result.put(fieldName, parser.toJson(fieldValue));
    }

    @Override
    protected boolean isAppliableHandler(Class<?> fieldType, Object fieldValue) {
        return fieldValue != null;
    }
}
