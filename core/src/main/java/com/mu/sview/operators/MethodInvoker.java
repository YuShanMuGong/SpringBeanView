package com.mu.sview.operators;

import com.mu.sview.dtos.BeanViewDto;

import java.lang.reflect.Method;
import java.util.Map;

public class MethodInvoker {

    private Map<String, BeanViewDto> singletonBeanViewDtoMap;

    public MethodInvoker(Map<String, BeanViewDto> singletonBeanViewDtoMap) {
        this.singletonBeanViewDtoMap = singletonBeanViewDtoMap;
    }

    public void invoke(String beanId , int index , ){
        Method method ;
        method.getTypeParameters()
    }

}
