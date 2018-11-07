package com.mu.sview.util;

import com.mu.sview.functions.TwoParamsConverter;

import java.util.*;
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

    public static <T> String join(Iterable<T> iter, String separator) {
        return join(iter, separator, Object::toString);
    }


    public static <T> String join(Iterable<T> iter, String separator, Function<? super T, ? extends String> toStringMethod) {
        if (iter == null) return null;
        Iterator<T> it = iter.iterator();
        StringBuilder returnStr = new StringBuilder();
        while (it.hasNext()) {
            T item = it.next();
            returnStr.append(toStringMethod.apply(item));
            if (it.hasNext()) {
                returnStr.append(separator);
            }
        }
        return returnStr.toString();
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

}
