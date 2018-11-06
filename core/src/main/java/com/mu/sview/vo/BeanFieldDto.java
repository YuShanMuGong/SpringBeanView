package com.mu.sview.vo;

import java.io.Serializable;
import java.util.List;

public class BeanFieldDto implements Serializable {

    private String name;
    private String typeClassName;
    private List<String> annotationNames;
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

    public List<String> getAnnotationNames() {
        return annotationNames;
    }

    public void setAnnotationNames(List<String> annotationNames) {
        this.annotationNames = annotationNames;
    }

    public String getViewAccessName() {
        return viewAccessName;
    }

    public void setViewAccessName(String viewAccessName) {
        this.viewAccessName = viewAccessName;
    }

}
