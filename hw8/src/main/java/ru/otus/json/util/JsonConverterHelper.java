package ru.otus.json.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * User: Vladimir Koba
 * Date: 08.08.2017
 * Time: 0:03
 */
public class JsonConverterHelper {

    public static JSONArray createJsonArrayFromJsonObject(JSONObject ob) {
        JSONArray ar = new JSONArray();
        for (Object o : ob.values()) {
            ar.add(o);
        }
        return ar;
    }
}
