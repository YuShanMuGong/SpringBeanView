package com.mu.sview.dtos;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class BeanViewDto implements Serializable {

    private String id;
    private String classFullName;
    private String classSimpleName;
    private Class beanClass;
    private List<BeanFieldDto> fields;
    private List<BeanFieldDto> staticFields;
    private List<BeanMethodDto> methods;
    private List<BeanMethodDto> staticMethods;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public String getClassSimpleName() {
        return classSimpleName;
    }

    public void setClassSimpleName(String classSimpleName) {
        this.classSimpleName = classSimpleName;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public List<BeanFieldDto> getFields() {
        return fields;
    }

    public void setFields(List<BeanFieldDto> fields) {
        this.fields = fields;
    }

    public List<BeanFieldDto> getStaticFields() {
        return staticFields;
    }

    public void setStaticFields(List<BeanFieldDto> staticFields) {
        this.staticFields = staticFields;
    }

    public List<BeanMethodDto> getMethods() {
        return methods;
    }

    public void setMethods(List<BeanMethodDto> methods) {
        this.methods = methods;
    }

    public List<BeanMethodDto> getStaticMethods() {
        return staticMethods;
    }

    public void setStaticMethods(List<BeanMethodDto> staticMethods) {
        this.staticMethods = staticMethods;
    }
}
