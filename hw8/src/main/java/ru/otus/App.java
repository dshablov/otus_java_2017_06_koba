package ru.otus;


import com.google.gson.Gson;
import ru.otus.json.KJsonParser;
import ru.otus.testobject.ArrayObject;
import ru.otus.testobject.ListObject;
import ru.otus.testobject.SimpleObject;

public class App {
    public static void main(String[] args) {
        KJsonParser parser = new KJsonParser();
        Gson gson = new Gson();
        System.out.println(parser.toJson(new SimpleObject("asdf", 123, new SimpleObject("gfds", 321, null))));
        System.out.println(gson.toJson(new SimpleObject("asdf", 123, new SimpleObject("gfds", 321, null))));

        System.out.println(parser.toJson(new ArrayObject()));
        System.out.println(gson.toJson(new ArrayObject()));

        System.out.println(parser.toJson(new ListObject()));
        System.out.println(gson.toJson(new ListObject()));

         }


}
