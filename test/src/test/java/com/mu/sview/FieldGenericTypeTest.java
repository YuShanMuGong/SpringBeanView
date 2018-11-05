package com.mu.sview;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class FieldGenericTypeTest {

    public static class Person {
        private List<String> tags;
    }

    @Test
    public void test() throws NoSuchFieldException {

        Class<Person> pCl = Person.class;

        Field field = pCl.getDeclaredField("tags");



        ParameterizedType type = (ParameterizedType) field.getGenericType();

        System.out.println(type.getRawType().getTypeName());
        System.out.println(type.getActualTypeArguments()[0].getTypeName());

    }

}
