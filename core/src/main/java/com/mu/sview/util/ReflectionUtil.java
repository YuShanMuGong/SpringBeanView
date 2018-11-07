package com.mu.sview.util;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtil {

    private static Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Field getField(Class<?> cl, String fieldName) {
        try {
            return cl.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            logger.error("ReflectionUtil", e);
            return null;
        }
    }

    public static Field getHaveAccessField(Class<?> cl, String fieldName) {
        try {
            Field f = cl.getDeclaredField(fieldName);
            if (!f.isAccessible()) f.setAccessible(true);
            return f;
        } catch (NoSuchFieldException e) {
            logger.error("ReflectionUtil", e);
            return null;
        }
    }

    public static List<Field> getFieldList(Class<?> cl) {
        Field[] fields = cl.getDeclaredFields();
        return Lists.newArrayList(fields);
    }

    public static List<Method> getMethodList(Class<?> cl) {
        Method[] methods = cl.getMethods();
        return Lists.newArrayList(methods);
    }

    public static void setFieldValue(Field field, Object obj, Object value) {
        if (!field.isAccessible()) field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            logger.error("ReflectionUtil", e);
        }
    }

    public static boolean isStatic(Method method) {
        if (method == null) return false;
        int mods = method.getModifiers();
        return Modifier.isStatic(mods);
    }

    public static boolean isStatic(Field field) {
        if (field == null) return false;
        int mods = field.getModifiers();
        return Modifier.isStatic(mods);
    }

    public static List<Annotation> getAnnotations(Field field) {
        if (field == null) return new ArrayList<>();
        return Arrays.asList(field.getDeclaredAnnotations());
    }

    public static boolean haveSuperClass(Class<?> cl) {
        return cl.getSuperclass() != null;
    }


    public static Class<?> getSuperClass(Class<?> subClass, int deep) {
        Class<?> cl = subClass;
        for (int i = 0; i < deep; i++) {
            cl = cl.getSuperclass();
        }
        return cl;
    }

}
