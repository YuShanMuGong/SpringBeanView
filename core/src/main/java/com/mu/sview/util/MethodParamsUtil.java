package com.mu.sview.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

public class MethodParamsUtil {

    // 参数 负责的对象，使用JSON表示（必然有大括号）
    public static Object[] getParamsValue(String params, Class<?>[] classes) {
        if (StringUtils.isEmpty(params)) return null;
        int size = classes.length;
        JSONObject jsonObject = JSON.parseObject(params);
        Object[] objs = new Object[size];
        for (int i = 0; i < size; i++) {
            objs[i] = jsonObject.getObject("v" + i, classes[i]);
        }
        return objs;
    }

}
