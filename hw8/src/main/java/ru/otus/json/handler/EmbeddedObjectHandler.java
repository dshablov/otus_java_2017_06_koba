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
    public void handle(JSONObject result, String fieldName, Class<?> fieldType, Object fieldValue) {
        try {
            if (fieldValue != null) {
                result.put(fieldName, parser.toJson(fieldValue));
                return;
            }

            if (hasNext()) {
                handleNext(result, fieldName,fieldType, fieldValue);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}
