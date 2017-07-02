package ru.otus.kunit.core;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import ru.otus.kunit.annotation.After;
import ru.otus.kunit.annotation.Before;
import ru.otus.kunit.annotation.Test;
import ru.otus.kunit.utils.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * User: Vladimir Koba
 * Date: 01.07.2017
 * Time: 21:26
 */
public class KUnit {

    public static void runTests(Class<?> testClass) {
        System.out.println("From class " + testClass.getName());
        Method beforeMethod = ReflectionHelper.findAnnotatedMethod(testClass, Before.class);
        Method afterMethod = ReflectionHelper.findAnnotatedMethod(testClass, After.class);
        Method[] methods = testClass.getMethods();
        for (Method method : methods) {
            if (isTestMethod(method)) {
                new KTestExecutor(testClass, beforeMethod, method, afterMethod).executeTest();
            }

        }
    }

    public static void runTests(Class<?>[] testClasses) {
        for (Class clazz : testClasses) {
            runTests(clazz);
        }
    }

    public static void runTests(String rootTestPackage) {
        Set<Class<?>> testClasses = ReflectionHelper.loadClassesFromPackage(rootTestPackage);
        runTests(testClasses.toArray(new Class[0]));
    }

    
    private static boolean isTestMethod(Method method) {
        return method.getAnnotation(Test.class) != null;
    }


}
