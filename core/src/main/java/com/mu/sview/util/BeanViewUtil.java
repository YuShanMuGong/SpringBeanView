package com.mu.sview.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.mu.sview.entry.BeanField;
import com.mu.sview.entry.BeanMethod;
import com.mu.sview.entry.BeanView;
import com.mu.sview.vo.BeanFieldDto;
import com.mu.sview.vo.BeanListVo;
import com.mu.sview.vo.BeanMethodDto;
import com.mu.sview.vo.BeanViewDto;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BeanViewUtil {

    public static BeanView buildBeanViewDto(String beanId, Object beanObj, int deep) {
        if (StringUtils.isEmpty(beanId) || beanObj == null) return null;
        BeanView beanView = new BeanView();
        beanView.setId(beanId);
        Class<?> beanClass = ReflectionUtil.getSuperClass(beanObj.getClass(), deep);
        beanView.setClassFullName(beanClass.getName());
        beanView.setClassSimpleName(beanClass.getSimpleName());
        beanView.setBeanClass(beanClass);
        List<Field> fields = ReflectionUtil.getFieldList(beanClass);
        beanView.setFields(getFieldList(fields));
        beanView.setStaticFields(getStaticFieldList(fields));
        List<Method> methods = ReflectionUtil.getMethodList(beanClass);
        beanView.setMethods(getMethodList(methods));
        beanView.setStaticMethods(getStaticMethodList(methods));
        beanView.setBeanObj(beanObj);

        Class<?> superClass = beanClass.getSuperclass();
        String superClassName = superClass.getSimpleName();
        String superClassFullName = superClass.getName();
        beanView.setSuperClass(superClass);
        beanView.setSuperClassSimpleName(superClassName);
        beanView.setSuperClassFullName(superClassFullName);
        return beanView;
    }

    private static List<BeanField> getFieldList(List<Field> fields) {
        if (CollectionUtils.isEmpty(fields)) return new ArrayList<>();
        return fields.stream()
                .filter(it -> !ReflectionUtil.isStatic(it))
                .map(BeanViewUtil::buildField)
                .collect(Collectors.toList());
    }

    private static List<BeanField> getStaticFieldList(List<Field> fields) {
        if (CollectionUtils.isEmpty(fields)) return new ArrayList<>();
        return fields.stream()
                .filter(ReflectionUtil::isStatic)
                .map(BeanViewUtil::buildField)
                .collect(Collectors.toList());
    }

    private static List<BeanMethod> getMethodList(List<Method> methods) {
        if (CollectionUtils.isEmpty(methods)) return new ArrayList<>();
        return methods.stream()
                .filter(it -> !ReflectionUtil.isStatic(it))
                .map(BeanViewUtil::buildMethod)
                .collect(Collectors.toList());
    }

    private static List<BeanMethod> getStaticMethodList(List<Method> methods) {
        if (CollectionUtils.isEmpty(methods)) return new ArrayList<>();
        return methods.stream()
                .filter(ReflectionUtil::isStatic)
                .map(BeanViewUtil::buildMethod)
                .collect(Collectors.toList());
    }

    public static BeanMethod buildMethod(Method method) {
        if (method == null) return null;
        BeanMethod dto = new BeanMethod();
        dto.setAccess(ViewAccessEnumsUtil.getViewAccessType(method.getModifiers()));
        dto.setReturnType(method.getReturnType());
        dto.setArgClasses(Lists.newArrayList(method.getParameterTypes()));
        dto.setName(method.getName());
        return dto;
    }

    public static BeanField buildField(Field field) {
        if (field == null) return null;
        BeanField dto = new BeanField();
        dto.setName(field.getName());
        dto.setCl(field.getType());
        dto.setAccess(ViewAccessEnumsUtil.getViewAccessType(field.getModifiers()));
        dto.setAnnotations(ReflectionUtil.getAnnotations(field));
        return dto;
    }

    public static BeanListVo convertListVo(BeanView dto) {
        if (dto == null) return null;
        BeanListVo vo = new BeanListVo();
        vo.setId(dto.getId());
        vo.setClassName(dto.getClassFullName());
        return vo;
    }

    public static BeanFieldDto convert(BeanField dto) {
        if (dto == null) return null;
        BeanFieldDto vo = new BeanFieldDto();
        vo.setName(dto.getName());
        vo.setAnnotationNames(listAnnotationName(dto.getAnnotations()));
        vo.setTypeClassName(dto.getCl().getName());
        vo.setViewAccessName(dto.getAccess().name);
        return vo;
    }

    public static BeanMethodDto convert(BeanMethod dto) {
        if (dto == null) return null;
        BeanMethodDto vo = new BeanMethodDto();
        vo.setName(dto.getName());
        vo.setArgNames(listClassName(dto.getArgClasses()));
        vo.setReturnTypeName(dto.getReturnType().getName());
        vo.setViewAccessName(dto.getAccess().name);
        vo.setDesc(generateMethodDesc(dto.getReturnType(), dto.getArgClasses()));
        return vo;
    }

    public static String generateMethodDesc(Class<?> returnClass, List<Class<?>> paramsClass) {
        StringBuilder sb = new StringBuilder();
        sb.append(returnClass.hashCode()).append(":");
        if (CollectionUtils.isEmpty(paramsClass)) return sb.toString();
        List<Integer> paramHashCodes = CollectionUtils.notNullMap(paramsClass, Class::hashCode);
        return Joiner.on(",").join(paramHashCodes);
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

    public static BeanViewDto convert(BeanView entry) {
        if (entry == null) return null;
        BeanViewDto beanViewDto = new BeanViewDto();
        beanViewDto.setId(entry.getId());
        beanViewDto.setClassFullName(entry.getClassFullName());
        beanViewDto.setClassSimpleName(entry.getClassSimpleName());
        beanViewDto.setFields(CollectionUtils.notNullMap(entry.getFields(), BeanViewUtil::convert));
        beanViewDto.setStaticFields(CollectionUtils.notNullMap(entry.getStaticFields(), BeanViewUtil::convert));

        beanViewDto.setMethods(CollectionUtils.notNullMap(entry.getMethods(), BeanViewUtil::convert));
        beanViewDto.setStaticMethods(CollectionUtils.notNullMap(entry.getMethods(), BeanViewUtil::convert));
        return beanViewDto;
    }

    public static Method getMethod(Class<?> cl, String methodName, String desc) {
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods) {
            if (!method.getName().equals(methodName)) continue;
            String d = generateMethodDesc(method.getReturnType(), Lists.newArrayList(method.getParameterTypes()));
            if (d.equals(desc)) return method;
        }
        return null;
    }

}
