package ru.otus.json;

import ru.otus.json.handler.PrimitiveHandler;
import ru.otus.json.handler.StringHandler;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * User: Vladimir Koba
 * Date: 04.08.2017
 * Time: 0:28
 */
public class TypeHandlerFactory {

    public static TypeHandler createChain() {
        List<TypeHandler> handlers = asList(
                new PrimitiveHandler(),
                new StringHandler()
        );

        if (handlers.size() == 0) {
            return null;
        }
        if (handlers.size() == 1) {
            return handlers.get(0);
        }
        TypeHandler currentHandler = handlers.get(0);
        for (int i = 1; i < handlers.size(); i++) {
            currentHandler = currentHandler.linkWith(handlers.get(i));
        }
        return handlers.get(0);
    }
}
