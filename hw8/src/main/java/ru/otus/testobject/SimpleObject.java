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


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        SimpleObject that = (SimpleObject) object;

        if (simpleInt != that.simpleInt) return false;
        if (simpleString != null ? !simpleString.equals(that.simpleString) : that.simpleString != null) return false;
        return simpleObject != null ? simpleObject.equals(that.simpleObject) : that.simpleObject == null;
    }

    @Override
    public int hashCode() {
        int result = simpleString != null ? simpleString.hashCode() : 0;
        result = 31 * result + simpleInt;
        result = 31 * result + (simpleObject != null ? simpleObject.hashCode() : 0);
        return result;
    }
}
