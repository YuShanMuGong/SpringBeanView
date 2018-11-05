package com.mu.sview.util;

import com.mu.sview.enums.ViewAccessEnums;

import java.lang.reflect.Modifier;

public class ViewAccessEnumsUtil {

    public static ViewAccessEnums getViewAccessType(int mods) {
        if (Modifier.isPrivate(mods)) return ViewAccessEnums.PRIVATE;
        if (Modifier.isPublic(mods)) return ViewAccessEnums.PUBLIC;
        if (Modifier.isProtected(mods)) return ViewAccessEnums.PROTECTED;
        return ViewAccessEnums.PACKAGE;
    }

}
