package com.mu.sview;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassFiledTest {

    @Test
    public void test() {

        Class<?> cl = ClassB.class;

        Field[] fields = cl.getDeclaredFields();

        System.out.println("fields size = " + fields.length);

        Method[] methods = cl.getSuperclass().getDeclaredMethods();
        System.out.println("methods size = " + methods.length);

    }

}
