package ru.otus.testobject;

/**
 * User: Vladimir Koba
 * Date: 30.07.2017
 * Time: 23:42
 */
public class SimpleObject {
    private String simpleString;
    private int simpleInt;
    private SimpleObject simpleObject;

    public SimpleObject(String simpleString, int simpleInt, SimpleObject simpleObject) {
        this.simpleString = simpleString;
        this.simpleInt = simpleInt;
        this.simpleObject = simpleObject;
    }

    public SimpleObject() {
    }

    public String getSimpleString() {
        return simpleString;
    }

    public void setSimpleString(String simpleString) {
        this.simpleString = simpleString;
    }

    public int getSimpleInt() {
        return simpleInt;
    }

    public void setSimpleInt(int simpleInt) {
        this.simpleInt = simpleInt;
    }

    public SimpleObject getSimpleObject() {
        return simpleObject;
    }

    public void setSimpleObject(SimpleObject simpleObject) {
        this.simpleObject = simpleObject;
    }
}
