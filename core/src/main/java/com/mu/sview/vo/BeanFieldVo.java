package com.mu.sview.vo;


public class BeanFieldVo {

    private String name;
    private String typeClassName;
    private String annotationNameDesc;
    private String viewAccessName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeClassName() {
        return typeClassName;
    }

    public void setTypeClassName(String typeClassName) {
        this.typeClassName = typeClassName;
    }

    public String getAnnotationNameDesc() {
        return annotationNameDesc;
    }

    public void setAnnotationNameDesc(String annotationNameDesc) {
        this.annotationNameDesc = annotationNameDesc;
    }

    public String getViewAccessName() {
        return viewAccessName;
    }

    public void setViewAccessName(String viewAccessName) {
        this.viewAccessName = viewAccessName;
    }
}
