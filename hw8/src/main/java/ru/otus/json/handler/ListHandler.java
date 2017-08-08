package ru.otus.json.handler;

import org.json.simple.JSONObject;
import ru.otus.json.KJsonParser;
import ru.otus.json.factory.TypeHandlerFactory;

import java.util.List;

import static ru.otus.json.util.JsonConverterHelper.createJsonArrayFromJsonObject;

/**
 * User: Vladimir Koba
 * Date: 07.08.2017
 * Time: 23:59
 */
public class ListHandler extends TypeHandler {
    private final KJsonParser parser;

    public ListHandler(KJsonParser parser) {
        this.parser = parser;
    }


    @Override
    protected void applyHandler(JSONObject result, String fieldName, Object fieldValue) {
        TypeHandler typeHandler = TypeHandlerFactory.createChain(parser);
        JSONObject ob = new JSONObject();
        List list = (List) fieldValue;
        for (int i = 0; i < list.size(); i++) {
            typeHandler.handle(ob, i + "", list.get(i).getClass(), list.get(i));
        }
        result.put(fieldName, createJsonArrayFromJsonObject(ob));
    }

    @Override
    protected boolean isAppliableHandler(Class<?> fieldType, Object fieldValue) {
        return fieldType.getSimpleName().equals("List");
    }
}
