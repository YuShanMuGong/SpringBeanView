package com.mu.sview.util;

import com.google.common.collect.Lists;
import com.mu.sview.dtos.BeanFieldDto;
import com.mu.sview.dtos.BeanMethodDto;
import com.mu.sview.dtos.BeanViewDto;
import com.mu.sview.vo.BeanFieldVo;
import com.mu.sview.vo.BeanListVo;
import com.mu.sview.vo.BeanMethodVo;
import com.mu.sview.vo.BeanVo;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BeanViewUtil {

    public static BeanViewDto buildBeanViewDto(String beanId, Object beanObj) {
        if (StringUtils.isEmpty(beanId) || beanObj == null) return null;
        BeanViewDto beanViewDto = new BeanViewDto();
        beanViewDto.setId(beanId);
        Class<?> beanClass = beanObj.getClass();
        beanViewDto.setClassFullName(beanClass.getName());
        beanViewDto.setClassSimpleName(beanClass.getSimpleName());
        beanViewDto.setBeanClass(beanClass);
        List<Field> fields = ReflectionUtil.getFieldList(beanClass);
        beanViewDto.setFields(getFieldList(fields));
        beanViewDto.setStaticFields(getStaticFieldList(fields));

        List<Method> methods = ReflectionUtil.getMethodList(beanClass);
        beanViewDto.setMethods(getMethodList(methods));
        beanViewDto.setStaticMethods(getStaticMethodList(methods));
        return beanViewDto;
    }

    private static List<BeanFieldDto> getFieldList(List<Field> fields) {
        if (CollectionUtils.isEmpty(fields)) return new ArrayList<>();
        return fields.stream()
                .filter(it -> !ReflectionUtil.isStatic(it))
                .map(BeanViewUtil::buildFieldDto)
                .collect(Collectors.toList());
    }

    private static List<BeanFieldDto> getStaticFieldList(List<Field> fields) {
        if (CollectionUtils.isEmpty(fields)) return new ArrayList<>();
        return fields.stream()
                .filter(ReflectionUtil::isStatic)
                .map(BeanViewUtil::buildFieldDto)
                .collect(Collectors.toList());
    }

    private static List<BeanMethodDto> getMethodList(List<Method> methods) {
        if (CollectionUtils.isEmpty(methods)) return new ArrayList<>();
        return methods.stream()
                .filter(it -> !ReflectionUtil.isStatic(it))
                .map(BeanViewUtil::buildMethodDto)
                .collect(Collectors.toList());
    }

    private static List<BeanMethodDto> getStaticMethodList(List<Method> methods) {
        if (CollectionUtils.isEmpty(methods)) return new ArrayList<>();
        return methods.stream()
                .filter(ReflectionUtil::isStatic)
                .map(BeanViewUtil::buildMethodDto)
                .collect(Collectors.toList());
    }

    public static BeanMethodDto buildMethodDto(Method method) {
        if (method == null) return null;
        BeanMethodDto dto = new BeanMethodDto();
        dto.setAccess(ViewAccessEnumsUtil.getViewAccessType(method.getModifiers()));
        dto.setReturnType(method.getReturnType());
        dto.setArgClasses(Lists.newArrayList(method.getParameterTypes()));
        dto.setName(method.getName());
        return dto;
    }

    public static BeanFieldDto buildFieldDto(Field field) {
        if (field == null) return null;
        BeanFieldDto dto = new BeanFieldDto();
        dto.setName(field.getName());
        dto.setCl(field.getType());
        dto.setAccess(ViewAccessEnumsUtil.getViewAccessType(field.getModifiers()));
        dto.setAnnotations(ReflectionUtil.getAnnotations(field));
        return dto;
    }

    public static BeanListVo convertListVo(BeanViewDto dto) {
        if (dto == null) return null;
        BeanListVo vo = new BeanListVo();
        vo.setId(dto.getId());
        vo.setClassName(dto.getClassFullName());
        return vo;
    }

    public static BeanFieldVo convert(BeanFieldDto dto) {
        if (dto == null) return null;
        BeanFieldVo vo = new BeanFieldVo();
        vo.setName(dto.getName());
        vo.setAnnotationNames(listAnnotationName(dto.getAnnotations()));
        vo.setTypeClassName(dto.getCl().getName());
        vo.setViewAccessName(dto.getAccess().name);
        return vo;
    }

    public static BeanMethodVo convert(BeanMethodDto dto, int index) {
        if (dto == null) return null;
        BeanMethodVo vo = new BeanMethodVo();
        vo.setName(dto.getName());
        vo.setArgNames(listClassName(dto.getArgClasses()));
        vo.setReturnTypeName(dto.getReturnType().getName());
        vo.setViewAccessName(dto.getAccess().name);
        vo.setIndex(index);
        return vo;
    }

    private static List<String> listClassName(List<Class<?>> classes) {
        if (CollectionUtils.isEmpty(classes)) return new ArrayList<>();
        return classes.stream().map(Class::getName)
                .collect(Collectors.toList());
    }

    private static List<String> listAnnotationName(List<Annotation> annotations) {
        if (CollectionUtils.isEmpty(annotations)) return new ArrayList<>();
        return annotations.stream().map(it -> it.getClass().getName())
                .collect(Collectors.toList());
    }

    public static BeanVo convert(BeanViewDto dto) {
        if (dto == null) return null;
        BeanVo beanVo = new BeanVo();
        beanVo.setId(dto.getId());
        beanVo.setClassFullName(dto.getClassFullName());
        beanVo.setClassSimpleName(dto.getClassSimpleName());
        beanVo.setFields(CollectionUtils.notNullMap(dto.getFields(), BeanViewUtil::convert));
        beanVo.setStaticFields(CollectionUtils.notNullMap(dto.getStaticFields(), BeanViewUtil::convert));

        beanVo.setMethods(CollectionUtils.notNullMapWithIndex(dto.getMethods(), BeanViewUtil::convert));
        beanVo.setStaticMethods(CollectionUtils.notNullMapWithIndex(dto.getMethods(), BeanViewUtil::convert));
        return beanVo;
    }

}
