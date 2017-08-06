package ru.otus.json;


import org.json.simple.JSONObject;

import java.lang.reflect.Field;

/**
 * User: Vladimir Koba
 * Date: 01.08.2017
 * Time: 21:51
 */
public abstract class TypeHandler {
    private TypeHandler next;

    //
    public abstract boolean handle(JSONObject result, Field field, Object object);

    public TypeHandler handleNext(JSONObject result, Field field, Object object) {
        next.handle(result, field, object);
        return next;
    }

    public TypeHandler linkWith(TypeHandler typeHandler) {
        this.next = typeHandler;
        return next;
    }

    public boolean applyChain(JSONObject result, Field field, Object object) {
        while (!handle(result, field, object)) {
            handleNext(result, field, object);
        }

//        boolean isHandlerApply = handle(result, field, object);
//        if (isHandlerApply) {
//            return true;
//        }
//        if (next == null) {
//            return false;
//        }
//        return next.handle(result, field, object);
    }

}
