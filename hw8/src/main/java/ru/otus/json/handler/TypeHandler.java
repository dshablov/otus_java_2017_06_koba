package ru.otus.json.handler;


import org.json.simple.JSONObject;

/**
 * User: Vladimir Koba
 * Date: 01.08.2017
 * Time: 21:51
 */
public abstract class TypeHandler {
    private TypeHandler next;


    public void handle(JSONObject result, String fieldName, Class<?> fieldType, Object fieldValue) {
        try {
            if (isAppliableHandler(fieldType, fieldValue)) {
                applyHandler(result, fieldName, fieldValue);
                return;
            }
            if (hasNext()) {
                handleNext(result, fieldName, fieldType, fieldValue);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void applyHandler(JSONObject result, String fieldName, Object fieldValue);

    protected abstract boolean isAppliableHandler(Class<?> fieldType, Object fieldValue);


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
