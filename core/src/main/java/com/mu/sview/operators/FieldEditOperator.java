package com.mu.sview.operators;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mu.sview.dtos.Response;
import com.mu.sview.util.ReflectionUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldEditOperator {

    private ConfigurableBeanFactory beanFactory;

    public FieldEditOperator(ConfigurableBeanFactory factory) {
        this.beanFactory = factory;
    }

    public Response<String> edit(String beanId, int deep, String fieldName, String newValue) {
        Object beanObj = beanFactory.getSingleton(beanId);
        if (beanObj == null) return Response.error("bean is null");
        Class<?> superClass = ReflectionUtil.getSuperClass(beanObj.getClass(), deep);
        try {
            Field field = ReflectionUtil.getHaveAccessField(superClass, fieldName);
            if (Modifier.isFinal(field.getModifiers())) {
                return Response.error("final field can not edit");
            }
            Class<?> fieldClass = field.getClass();
            JSONObject json = JSON.parseObject(newValue);
            Object newObj = json.getObject("v", fieldClass);
            field.set(beanObj, newObj);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
        return Response.success();
    }


}
