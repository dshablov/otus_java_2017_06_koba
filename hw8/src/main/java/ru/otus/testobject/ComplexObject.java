package ru.otus.testobject;

import java.util.List;

/**
 * User: Vladimir Koba
 * Date: 30.07.2017
 * Time: 23:40
 */
public class ComplexObject {
    private String stringField;
    private String [] stringArray;
    private int intField;
    private int [] intArray;
    private List<String> listStringField;
    private SimpleObject simpleObject;
    private List<SimpleObject> listSimpleObject;


    public ComplexObject(String stringField, String[] stringArray, int intField, int[] intArray, List<String> listStringField, SimpleObject simpleObject, List<SimpleObject> listSimpleObject) {
        this.stringField = stringField;
        this.stringArray = stringArray;
        this.intField = intField;
        this.intArray = intArray;
        this.listStringField = listStringField;
        this.simpleObject = simpleObject;
        this.listSimpleObject = listSimpleObject;
    }

    public ComplexObject() {
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public String[] getStringArray() {
        return stringArray;
    }

    public void setStringArray(String[] stringArray) {
        this.stringArray = stringArray;
    }

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public int[] getIntArray() {
        return intArray;
    }

    public void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }

    public List<String> getListStringField() {
        return listStringField;
    }

    public void setListStringField(List<String> listStringField) {
        this.listStringField = listStringField;
    }

    public SimpleObject getSimpleObject() {
        return simpleObject;
    }

    public void setSimpleObject(SimpleObject simpleObject) {
        this.simpleObject = simpleObject;
    }

    public List<SimpleObject> getListSimpleObject() {
        return listSimpleObject;
    }

    public void setListSimpleObject(List<SimpleObject> listSimpleObject) {
        this.listSimpleObject = listSimpleObject;
    }
}
