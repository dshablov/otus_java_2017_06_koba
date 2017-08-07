package ru.otus.json;


import org.json.simple.JSONObject;
import ru.otus.json.factory.TypeHandlerFactory;
import ru.otus.json.handler.TypeHandler;

import java.lang.reflect.Field;

/**
 * User: Vladimir Koba
 * Date: 31.07.2017
 * Time: 23:45
 */
public class KJsonParser {


    public JSONObject toJson(Object object) {
        JSONObject jsonObject = new JSONObject();
        return toJson(object, jsonObject);
    }

    private JSONObject toJson(Object object, JSONObject result) {
        try {
            if (object == null) {
                return new JSONObject();
            }
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                TypeHandler handlersChain = TypeHandlerFactory.createChain(this);
                handlersChain.handle(result, field.getName(), field.getType(), field.get(object));
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
