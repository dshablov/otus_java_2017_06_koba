package ru.otus.kunit.annotation;

import java.lang.annotation.*;

/**
 * User: Vladimir Koba
 * Date: 01.07.2017
 * Time: 21:18
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface After {
}
