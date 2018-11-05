package com.mu.sview.dtos;

import com.mu.sview.enums.ViewAccessEnums;

import java.lang.annotation.Annotation;
import java.util.List;

public class BeanFieldDto {

    private String name;
    private Class<?> cl;
    private ViewAccessEnums access;
    private List<Annotation> annotations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getCl() {
        return cl;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    public void setCl(Class<?> cl) {
        this.cl = cl;
    }

    public ViewAccessEnums getAccess() {
        return access;
    }

    public void setAccess(ViewAccessEnums access) {
        this.access = access;
    }
}
