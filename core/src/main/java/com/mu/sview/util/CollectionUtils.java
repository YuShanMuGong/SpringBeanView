package com.mu.sview.util;

import com.mu.sview.functions.TwoParamsConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionUtils {

    public static <T> List<T> notNullFilter(List<T> list, Predicate<? super T> condition) {
        if (isEmpty(list)) return new ArrayList<>();
        return list.stream().filter(Objects::nonNull)
                .filter(condition)
                .collect(Collectors.toList());
    }

    public static <A, B> List<B> notNullMap(List<A> list, Function<? super A, ? extends B> converter) {
        if (isEmpty(list)) return new ArrayList<>();
        return list.stream().filter(Objects::nonNull)
                .map(converter)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static <A, B> List<B> notNullMapWithIndex(List<A> list, TwoParamsConverter<? super A, Integer, ? extends B> converter) {
        if (isEmpty(list)) return new ArrayList<>();
        int size = list.size();
        List<B> blis = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            A it = list.get(0);
            blis.add(converter.convert(it, i));
        }
        return blis;
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

}
