package com.mu.sview.enums;

public enum MethodInvoke {

    STATIC(1, "static method invoke"),
    NO_STATIC(2, "not static method invoke"),
    ;

    public final int value;
    public final String name;


    MethodInvoke(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
