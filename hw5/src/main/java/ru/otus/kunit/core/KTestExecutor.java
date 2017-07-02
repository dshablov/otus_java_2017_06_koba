package ru.otus.kunit.core;

import ru.otus.kunit.exception.KAssertException;
import ru.otus.kunit.utils.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * User: Vladimir Koba
 * Date: 02.07.2017
 * Time: 19:12
 */
public class KTestExecutor {


    private final Class testClass;
    private final Method beforeMethod;
    private final Method testMethod;
    private final Method afterMethod;

    public KTestExecutor(Class testClass, Method beforeMethod, Method testMethod, Method afterMethod) {
        this.testClass = testClass;
        this.beforeMethod = beforeMethod;
        this.testMethod = testMethod;
        this.afterMethod = afterMethod;
    }

    public void executeTest() {

        try {
            System.out.println("Test " + testMethod.getName());
            Object testObject = ReflectionHelper.instantiate(testClass);
            invokeMethodIfExist(beforeMethod, testObject);
            testMethod.invoke(testObject);
            invokeMethodIfExist(afterMethod, testObject);
            System.out.println("Test success");
            System.out.println("************************************************************");
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof KAssertException) {
                System.out.println("Test assert failed. " + e.getTargetException().getMessage());
            } else {
                e.printStackTrace();
            }
            System.out.println("************************************************************");
        } catch (Exception e) {
            System.out.println("Test runtime failed.");
            e.printStackTrace();
        }
    }

    private void invokeMethodIfExist(Method method, Object testObject) throws IllegalAccessException, InvocationTargetException {
        if (method != null) {
            method.invoke(testObject);
        }
    }
}
