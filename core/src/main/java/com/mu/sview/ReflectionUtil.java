package com.mu.sview;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
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

    public static List<Field> getFieldList(Class<?> cl) {
        Field[] fields = cl.getDeclaredFields();
        return Lists.newArrayList(fields);
    }

    public static void setFieldValue(Field field, Object obj, Object value) {
        if (!field.isAccessible()) field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            logger.error("ReflectionUtil", e);
        }
    }

}
