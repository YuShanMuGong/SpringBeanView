package com.mu.sview.vo;


import java.io.Serializable;
import java.util.List;

public class BeanVo implements Serializable {

    private String id;
    private String classFullName;
    private Integer level;
    private String superClassName;
    private List<BeanFieldVo> fields;
    private List<BeanFieldVo> staticFields;
    private List<BeanMethodVo> methods;
    private List<BeanMethodVo> staticMethods;

    public String getId() {
        return id;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<BeanFieldVo> getFields() {
        return fields;
    }

    public void setFields(List<BeanFieldVo> fields) {
        this.fields = fields;
    }

    public List<BeanFieldVo> getStaticFields() {
        return staticFields;
    }

    public void setStaticFields(List<BeanFieldVo> staticFields) {
        this.staticFields = staticFields;
    }

    public List<BeanMethodVo> getMethods() {
        return methods;
    }

    public void setMethods(List<BeanMethodVo> methods) {
        this.methods = methods;
    }

    public List<BeanMethodVo> getStaticMethods() {
        return staticMethods;
    }

    public void setStaticMethods(List<BeanMethodVo> staticMethods) {
        this.staticMethods = staticMethods;
    }
}
