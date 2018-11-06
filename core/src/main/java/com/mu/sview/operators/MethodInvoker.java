package com.mu.sview.operators;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mu.sview.dtos.MethodInvokeDto;
import com.mu.sview.dtos.Response;
import com.mu.sview.util.BeanViewUtil;
import com.mu.sview.util.MethodParamsUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;


public class MethodInvoker {

    private ConfigurableBeanFactory beanFactory;

    public MethodInvoker(ConfigurableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Response<String> invoke(MethodInvokeDto invokeDto) {
        Object bean = beanFactory.getSingleton(invokeDto.getBeanId());
        if (bean == null) return Response.error("bean is null");
        Class<?> beanClass = bean.getClass();
        Method method = BeanViewUtil.getMethod(beanClass, invokeDto.getMethodName(), invokeDto.getDesc());
        if (method == null) return Response.error("method not found");
        String paramsJson = invokeDto.getParams();
        if (StringUtils.isEmpty(paramsJson)) {
            try {
                Object resObj = method.invoke(bean);
                return Response.success(JSON.toJSONString(resObj));
            } catch (Exception e) {
                return Response.error(e.getMessage());
            }
        }
        Class<?>[] paramsCls = method.getParameterTypes();
        Object[] methodParams = MethodParamsUtil.getParamsValue(paramsJson, paramsCls);
        try {
            Object res = method.invoke(bean, methodParams);
            return Response.error(JSON.toJSONString(res));
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

}
