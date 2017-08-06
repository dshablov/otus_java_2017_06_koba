package ru.otus.json;


import org.json.simple.JSONObject;
import ru.otus.json.handler.PrimitiveHandler;
import ru.otus.json.handler.StringHandler;

import java.lang.reflect.Field;
import java.util.List;

import static java.util.Arrays.asList;

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
                TypeHandler handlersChain = TypeHandlerFactory.createChain();
                handlersChain.applyChain(result,field,object);
//                Class<?> fieldType = field.getType();
//                if (fieldType.isPrimitive()) {
//                    result.put(field.getName(), field.get(object));
//                } else {
//                    switch (fieldType.getSimpleName()) {
//                    case "List":
//                        List list = (List) field.get(object);
//                        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
//                        Json.createObjectBuilder().add(field.getName(), list.stream().m)
//                        for (Object obj : list) {
//
//                            System.out.println(obj);
//                        }
////                        arrayBuilder.add(jsonObjectBuilder);
//                        builder.add(field.getName(), arrayBuilder);
//                        break;

                        default:
                    }
                }

            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
