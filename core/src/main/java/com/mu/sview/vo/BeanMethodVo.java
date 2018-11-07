package com.mu.sview.vo;


public class BeanMethodVo {

    private String name;
    private String viewAccessName;
    private String argNameDesc;
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

    public String getArgNameDesc() {
        return argNameDesc;
    }

    public void setArgNameDesc(String argNameDesc) {
        this.argNameDesc = argNameDesc;
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
