package ru.otus.json.handler;


import org.json.simple.JSONObject;

/**
 * User: Vladimir Koba
 * Date: 01.08.2017
 * Time: 21:51
 */
public abstract class TypeHandler {
    private TypeHandler next;


    protected abstract void handle(JSONObject result, String fieldName, Class<?> fieldType, Object fieldValue);
    

    protected TypeHandler handleNext(JSONObject result, String fieldName, Class<?> fieldType, Object fieldValue) {
        next.handle(result, fieldName, fieldType, fieldValue);
        return next;
    }

    public TypeHandler linkWith(TypeHandler typeHandler) {
        this.next = typeHandler;
        return next;
    }

    protected boolean hasNext() {
        return next != null;
    }


}
