package com.mu.sview.entry;

import com.mu.sview.enums.ViewAccessEnums;

import java.util.List;

public class BeanMethod {

    private String name;
    private Class<?> returnType;
    private List<Class<?>> argClasses;
    private ViewAccessEnums access;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    public List<Class<?>> getArgClasses() {
        return argClasses;
    }

    public void setArgClasses(List<Class<?>> argClasses) {
        this.argClasses = argClasses;
    }

    public ViewAccessEnums getAccess() {
        return access;
    }

    public void setAccess(ViewAccessEnums access) {
        this.access = access;
    }
}
