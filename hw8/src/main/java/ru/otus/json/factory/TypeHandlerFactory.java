package ru.otus.json.factory;

import ru.otus.json.KJsonParser;
import ru.otus.json.handler.*;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * User: Vladimir Koba
 * Date: 04.08.2017
 * Time: 0:28
 */
public class TypeHandlerFactory {
    private static TypeHandler chain;

    public static TypeHandler createChain(KJsonParser jsonParser) {
        if (chain != null) {
            return chain;
        }
        List<TypeHandler> handlers = asList(
                new PrimitiveHandler(),
                new StringHandler(),
                new ArrayHandler(jsonParser),
                new ListHandler(jsonParser),
                new EmbeddedObjectHandler(jsonParser)
        );

        if (handlers.size() == 0) {
            return new EmptyHandler();
        }
        if (handlers.size() == 1) {
            return handlers.get(0);
        }
        TypeHandler currentHandler = handlers.get(0);
        for (int i = 1; i < handlers.size(); i++) {
            currentHandler = currentHandler.linkWith(handlers.get(i));
        }
        chain = handlers.get(0);
        return chain;
    }
}
