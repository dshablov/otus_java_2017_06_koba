package ru.otus.json.handler;

import org.json.simple.JSONObject;
import ru.otus.json.KJsonParser;
import ru.otus.json.factory.TypeHandlerFactory;

import java.lang.reflect.Array;

import static ru.otus.json.util.JsonConverterHelper.createJsonArrayFromJsonObject;

/**
 * User: Vladimir Koba
 * Date: 07.08.2017
 * Time: 22:39
 */
public class ArrayHandler extends TypeHandler {
    private KJsonParser parser;

    public ArrayHandler(KJsonParser parser) {
        this.parser = parser;
    }

    @Override
    public void handle(JSONObject result, String fieldName, Class<?> fieldType, Object potentialArray) {
        try {
            if (fieldType.isArray()) {
                TypeHandler typeHandler = TypeHandlerFactory.createChain(parser);
                JSONObject ob = new JSONObject();
                for (int i = 0; i < Array.getLength(potentialArray); i++) {
                    typeHandler.handle(ob, i + "", Array.get(potentialArray, i).getClass(), Array.get(potentialArray, i));
                }
                result.put(fieldName, createJsonArrayFromJsonObject(ob));
                return;
            }
            if (hasNext()) {
                handleNext(result, fieldName, fieldType, potentialArray);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
