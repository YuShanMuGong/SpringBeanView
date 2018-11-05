package com.mu.sview.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FieldValueUtil {

    public static Object convertValue(Class<?> cl, String value) {
        if (cl == null) return null;
        if (cl == String.class) {
            return value;
        }
        if (cl == Integer.class || cl == int.class) {
            return Integer.valueOf(value);
        }
        if (cl == Long.class || cl == long.class) {
            return Long.valueOf(value);
        }
        if (cl == Byte.class || cl == byte.class) {
            return Byte.valueOf(value);
        }
        if (cl == Boolean.class || cl == boolean.class) {
            return Boolean.valueOf(value);
        }
        if (cl == Float.class || cl == float.class) {
            return Float.valueOf(value);
        }
        if (cl == Double.class || cl == double.class) {
            return Double.valueOf(value);
        }
        throw new IllegalStateException("不合法的参数类型");
    }

    public static Object convertValue(ParameterizedType type, String value) {
        if (type == null) return null;

        String rawTypeName = type.getRawType().getTypeName();

        if (rawTypeName.equals(String.class.getTypeName())) {
            return value;
        }
        if (rawTypeName.equals(Integer.class.getTypeName()) || rawTypeName.equals(int.class.getTypeName())) {
            return Integer.valueOf(value);
        }

        if (rawTypeName.equals(Long.class.getTypeName()) || rawTypeName.equals(long.class.getTypeName())) {
            return Long.valueOf(value);
        }

        if (rawTypeName.equals(Byte.class.getTypeName()) || rawTypeName.equals(byte.class.getTypeName())) {
            return Byte.valueOf(value);
        }

        if (rawTypeName.equals(Boolean.class.getTypeName()) || rawTypeName.equals(boolean.class.getTypeName())) {
            return Boolean.valueOf(value);
        }

        if (rawTypeName.equals(List.class.getTypeName())) {
            String paramsTypeName = type.getActualTypeArguments()[0].getTypeName();
            List<Object> list = new ArrayList<>();
            try {
                Class paramsClass = Class.forName(paramsTypeName);
                String[] its = StringUtils.split(value, ",");
                for (String it : its) {
                    list.add(convertValue(paramsClass, it));
                }
                return list;
            } catch (ClassNotFoundException e) {
            }
        }
        throw new IllegalStateException("不合法的参数类型！");
    }


}
