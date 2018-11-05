package com.mu.sview.dtos;

import java.io.Serializable;

public class Response<T extends Serializable> implements Serializable {

    public final T content;
    public final boolean successFlag;
    public final String msg;

    public static <A extends Serializable> Response<A> success(A a) {
        return new Response<>(a, true, null);
    }

    public static <A extends Serializable> Response<A> error(String msg) {
        return new Response<>(null, false, msg);
    }

    public static <A extends Serializable> Response<A> success() {
        return new Response<>(null, true, null);
    }

    public boolean isSuccess() {
        return successFlag;
    }

    public boolean isError(){
        return !successFlag;
    }

    private Response(T content, boolean successFlag, String msg) {
        this.content = content;
        this.successFlag = successFlag;
        this.msg = msg;
    }
}
