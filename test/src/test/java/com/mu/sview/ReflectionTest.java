package com.mu.sview;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionTest {

    @Test
    public void test_get_static_field() throws NoSuchFieldException {

        Class<?> cl = Person.class;

        Field field = cl.getDeclaredField("cName");

        int modifier = field.getModifiers();

        System.out.println(Modifier.isStatic(modifier));

    }

    @Test
    public void test_get_static_method() throws Exception {

        Class<?> cl = Person.class;
        Method[] methods = cl.getDeclaredMethods();

        for (Method method : methods) {
            if (method.getName().equals("showCName")) {
                int modifier = method.getModifiers();
                System.out.println(Modifier.isStatic(modifier));
            }
        }


    }

}
