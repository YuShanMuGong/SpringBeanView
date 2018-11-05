package com.mu.sview.enums;

public enum ViewAccessEnums {
    PUBLIC("PUBLIC", 1),
    PACKAGE("PACKAGE", 2),
    PROTECTED("PROTECTED", 3),
    PRIVATE("PRIVATE", 4)
    ;

    public final String name;
    public final int value;

    ViewAccessEnums(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
