package ru.otus.template;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Vladimir Koba
 * Date: 23.10.2017
 * Time: 23:27
 */
public class TemplateUtils {

    public static String getPage(String templateName, Map<String, Object> params) throws IOException {
        return TemplateProcessor.instance().getPage(templateName, params);
    }

    public static Map<String, Object> hitParams(long hits) {
        Map<String, Object> map = new HashMap<>();
        map.put("hits", hits);
        return map;
    }
}
