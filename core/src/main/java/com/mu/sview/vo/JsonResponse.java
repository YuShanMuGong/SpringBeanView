package com.mu.sview.vo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class JsonResponse implements Serializable {

    public final int code;
    public final String msg;
    public final Object content;

    public static JsonResponse success(Object content) {
        return new JsonResponse(0, null, content);
    }

    public static JsonResponse success() {
        return new JsonResponse(1, null, null);
    }

    public static JsonResponse error(String msg) {
        return new JsonResponse(1, msg, null);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    private JsonResponse(int code, String msg, Object content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getContent() {
        return content;
    }
}
