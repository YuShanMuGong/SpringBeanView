package com.mu.sview.dtos;

import java.io.Serializable;
import java.util.List;

public class BeanMethodDto implements Serializable {

    private String name;
    private String viewAccessName;
    private List<String> argNames;
    private String returnTypeName;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getViewAccessName() {
        return viewAccessName;
    }

    public void setViewAccessName(String viewAccessName) {
        this.viewAccessName = viewAccessName;
    }

    public List<String> getArgNames() {
        return argNames;
    }

    public void setArgNames(List<String> argNames) {
        this.argNames = argNames;
    }

    public String getReturnTypeName() {
        return returnTypeName;
    }

    public void setReturnTypeName(String returnTypeName) {
        this.returnTypeName = returnTypeName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
