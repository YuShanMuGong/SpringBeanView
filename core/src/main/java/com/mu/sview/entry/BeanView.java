package com.mu.sview.entry;

import java.io.Serializable;
import java.util.List;

public class BeanView implements Serializable {

    private String id;
    private String classFullName;
    private String classSimpleName;
    private Class beanClass;
    private Class superClass;
    private String superClassSimpleName;
    private String superClassFullName;
    private List<BeanField> fields;
    private List<BeanField> staticFields;
    private List<BeanMethod> methods;
    private List<BeanMethod> staticMethods;
    private Object beanObj;

    public Class getSuperClass() {
        return superClass;
    }

    public void setSuperClass(Class superClass) {
        this.superClass = superClass;
    }

    public String getSuperClassSimpleName() {
        return superClassSimpleName;
    }

    public void setSuperClassSimpleName(String superClassSimpleName) {
        this.superClassSimpleName = superClassSimpleName;
    }

    public String getSuperClassFullName() {
        return superClassFullName;
    }

    public void setSuperClassFullName(String superClassFullName) {
        this.superClassFullName = superClassFullName;
    }

    public Object getBeanObj() {
        return beanObj;
    }

    public void setBeanObj(Object beanObj) {
        this.beanObj = beanObj;
    }

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

    public List<BeanField> getFields() {
        return fields;
    }

    public void setFields(List<BeanField> fields) {
        this.fields = fields;
    }

    public List<BeanField> getStaticFields() {
        return staticFields;
    }

    public void setStaticFields(List<BeanField> staticFields) {
        this.staticFields = staticFields;
    }

    public List<BeanMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<BeanMethod> methods) {
        this.methods = methods;
    }

    public List<BeanMethod> getStaticMethods() {
        return staticMethods;
    }

    public void setStaticMethods(List<BeanMethod> staticMethods) {
        this.staticMethods = staticMethods;
    }
}
