package com.mu.sview.functions;

public interface TwoParamsConverter<A, B, C> {

    C convert(A a, B b);

}
