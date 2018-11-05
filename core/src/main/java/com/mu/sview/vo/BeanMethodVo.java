package com.mu.sview.vo;

import java.io.Serializable;
import java.util.List;

public class BeanMethodVo implements Serializable {

    private String name;
    private String viewAccessName;
    private List<String> argNames;
    private String returnTypeName;
    private int index;

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getReturnTypeName() {
        return returnTypeName;
    }

    public void setReturnTypeName(String returnTypeName) {
        this.returnTypeName = returnTypeName;
    }
}
