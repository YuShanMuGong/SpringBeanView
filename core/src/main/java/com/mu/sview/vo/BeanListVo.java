package com.mu.sview.vo;

import java.io.Serializable;
import java.util.List;

public class BeanListVo implements Serializable {

    private String id;
    private String className;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
